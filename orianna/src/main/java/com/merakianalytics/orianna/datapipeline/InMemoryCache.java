package com.merakianalytics.orianna.datapipeline;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.cache2k.CacheEntry;
import org.cache2k.expiry.ExpiryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.merakianalytics.datapipelines.AbstractDataStore;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sinks.Put;
import com.merakianalytics.datapipelines.sinks.PutMany;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.expiration.ExpirationPeriod;
import com.merakianalytics.orianna.types.UniqueKeys;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.core.GhostObject.ListProxy;
import com.merakianalytics.orianna.types.core.GhostObject.LoadHook;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.Items;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Masteries;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.Runes;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class InMemoryCache extends AbstractDataStore {
    public static class Configuration {
        private static final Map<String, ExpirationPeriod> DEFAULT_EXPIRATION_PERIODS = ImmutableMap.<String, ExpirationPeriod> builder()
            .put(Champion.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Item.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Items.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(LanguageStrings.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Languages.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Maps.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Mastery.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Masteries.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(ProfileIcon.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(ProfileIcons.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Realm.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Rune.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Runes.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(SummonerSpell.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(SummonerSpells.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Versions.class.getCanonicalName(), ExpirationPeriod.create(6, TimeUnit.HOURS))
            .put(Summoner.class.getCanonicalName(), ExpirationPeriod.create(30, TimeUnit.MINUTES))
            .build();

        private Map<String, ExpirationPeriod> expirationPeriods = DEFAULT_EXPIRATION_PERIODS;

        /**
         * @return the expirationPeriods
         */
        public Map<String, ExpirationPeriod> getExpirationPeriods() {
            return expirationPeriods;
        }

        /**
         * @param expirationPeriods
         *        the expirationPeriods to set
         */
        public void setExpirationPeriods(final Map<String, ExpirationPeriod> expirationPeriods) {
            this.expirationPeriods = expirationPeriods;
        }
    }

    private class Policy implements ExpiryPolicy<Integer, Object> {
        @Override
        public long calculateExpiryTime(final Integer key, final Object value, final long loadTime, final CacheEntry<Integer, Object> oldEntry) {
            return loadTime + expirationPeriods.get(value.getClass()).longValue();
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryCache.class);
    private final Cache<Integer, Object> cache;
    private final Map<Class<?>, Long> expirationPeriods;

    public InMemoryCache() {
        this(new Configuration());
    }

    public InMemoryCache(final Configuration config) {
        final Map<Class<?>, Long> periods = new HashMap<>();

        for(final String className : config.getExpirationPeriods().keySet()) {
            try {
                final Class<?> clazz = Class.forName(className);
                final ExpirationPeriod period = config.getExpirationPeriods().get(className);
                periods.put(clazz, period.getUnit().toMillis(period.getPeriod()));
            } catch(final ClassNotFoundException e) {
                LOGGER.error("Couldn't find class by name " + className + "!", e);
                throw new OriannaException("Couldn't find class by name " + className + "!", e);
            }
        }

        expirationPeriods = Collections.unmodifiableMap(periods);

        cache = new Cache2kBuilder<Integer, Object>() {}.disableLastModificationTime(true).disableStatistics(true).expiryPolicy(new Policy())
            .keepDataAfterExpired(false).permitNullValues(false).storeByReference(true).build();
    }

    @Get(Champion.class)
    public Champion getChampion(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionQuery(query);
        return (Champion)cache.get(key);
    }

    @Get(Item.class)
    public Item getItem(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forItemQuery(query);
        return (Item)cache.get(key);
    }

    @Get(Items.class)
    public Items getItems(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forItemsQuery(query);
        return (Items)cache.get(key);
    }

    @Get(Languages.class)
    public Languages getLanguages(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLanguagesQuery(query);
        return (Languages)cache.get(key);
    }

    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLanguageStringsQuery(query);
        return (LanguageStrings)cache.get(key);
    }

    @GetMany(Item.class)
    public CloseableIterator<Item> getManyItem(final Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyItemQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Item next() {
                final int key = iterator.next();
                return (Item)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Mastery.class)
    public CloseableIterator<Mastery> getManyMastery(final Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyMasteryQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Mastery>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Mastery next() {
                final int key = iterator.next();
                return (Mastery)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(ProfileIcon.class)
    public CloseableIterator<ProfileIcon> getManyProfileIcon(final Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyProfileIconQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<ProfileIcon>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProfileIcon next() {
                final int key = iterator.next();
                return (ProfileIcon)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Rune.class)
    public CloseableIterator<Rune> getManyRune(final Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyRuneQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Rune>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Rune next() {
                final int key = iterator.next();
                return (Rune)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Summoner.class)
    public CloseableIterator<Summoner> getManySummoner(final Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManySummonerQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Summoner>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Summoner next() {
                final int key = iterator.next();
                return (Summoner)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(SummonerSpell.class)
    public CloseableIterator<SummonerSpell> getManySummonerSpell(final Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManySummonerSpellQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<SummonerSpell>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public SummonerSpell next() {
                final int key = iterator.next();
                return (SummonerSpell)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(Maps.class)
    public Maps getMaps(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMapsQuery(query);
        return (Maps)cache.get(key);
    }

    @Get(Masteries.class)
    public Masteries getMasteries(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMasteriesQuery(query);
        return (Masteries)cache.get(key);
    }

    @Get(Mastery.class)
    public Mastery getMastery(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMasteryQuery(query);
        return (Mastery)cache.get(key);
    }

    @Get(ProfileIcon.class)
    public ProfileIcon getProfileIcon(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIconQuery(query);
        return (ProfileIcon)cache.get(key);
    }

    @Get(ProfileIcons.class)
    public ProfileIcons getProfileIcons(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIconsQuery(query);
        return (ProfileIcons)cache.get(key);
    }

    @Get(Realm.class)
    public Realm getRealm(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forRealmQuery(query);
        return (Realm)cache.get(key);
    }

    @Get(Rune.class)
    public Rune getRune(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forRuneQuery(query);
        return (Rune)cache.get(key);
    }

    @Get(Runes.class)
    public Runes getRunes(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forRunesQuery(query);
        return (Runes)cache.get(key);
    }

    @Get(Summoner.class)
    public Summoner getSummoner(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forSummonerQuery(query);
        return (Summoner)cache.get(key);
    }

    @Get(SummonerSpell.class)
    public SummonerSpell getSummonerSpell(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forSummonerSpellQuery(query);
        return (SummonerSpell)cache.get(key);
    }

    @Get(SummonerSpells.class)
    public SummonerSpells getSummonerSpells(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forSummonerSpellsQuery(query);
        return (SummonerSpells)cache.get(key);
    }

    @Get(Versions.class)
    public Versions getVersions(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forVersionsQuery(query);
        return (Versions)cache.get(key);
    }

    @Put(Champion.class)
    public void putChampion(final Champion champion, final PipelineContext context) {
        final int[] keys = UniqueKeys.forChampion(champion);

        if(keys.length < 3) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putChampion(champion, null);
                }
            };

            champion.registerGhostLoadHook(hook, Champion.CHAMPION_LOAD_GROUP);
            champion.registerGhostLoadHook(hook, Champion.STATUS_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, champion);
        }
    }

    @Put(Item.class)
    public void putItem(final Item item, final PipelineContext context) {
        final int[] keys = UniqueKeys.forItem(item);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putItem(item, null);
                }
            };

            item.registerGhostLoadHook(hook, Item.ITEM_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, item);
        }
    }

    @Put(Items.class)
    public void putItems(final Items items, final PipelineContext context) {
        final int key = UniqueKeys.forItems(items);
        cache.put(key, items);

        if(items.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putItems(items, null);
                }
            };

            items.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyItem(items, context);
        }
    }

    @Put(Languages.class)
    public void putLanguages(final Languages languages, final PipelineContext context) {
        final int key = UniqueKeys.forLanguages(languages);
        cache.put(key, languages);
    }

    @Put(Versions.class)
    public void putLanguages(final Versions versions, final PipelineContext context) {
        final int key = UniqueKeys.forVersions(versions);
        cache.put(key, versions);
    }

    @Put(LanguageStrings.class)
    public void putLanguageStrings(final LanguageStrings languageStrings, final PipelineContext context) {
        final int key = UniqueKeys.forLanguageStrings(languageStrings);
        cache.put(key, languageStrings);
    }

    @PutMany(Item.class)
    public void putManyItem(final Iterable<Item> items, final PipelineContext context) {
        for(final Item item : items) {
            putItem(item, context);
        }
    }

    @PutMany(Mastery.class)
    public void putManyMastery(final Iterable<Mastery> masteries, final PipelineContext context) {
        for(final Mastery mastery : masteries) {
            putMastery(mastery, context);
        }
    }

    @PutMany(ProfileIcon.class)
    public void putManyProfileIcon(final Iterable<ProfileIcon> icons, final PipelineContext context) {
        for(final ProfileIcon icon : icons) {
            putProfileIcon(icon, context);
        }
    }

    @PutMany(Rune.class)
    public void putManyRune(final Iterable<Rune> runes, final PipelineContext context) {
        for(final Rune rune : runes) {
            putRune(rune, context);
        }
    }

    @PutMany(Summoner.class)
    public void putManySummoner(final Iterable<Summoner> summoners, final PipelineContext context) {
        for(final Summoner summoner : summoners) {
            putSummoner(summoner, context);
        }
    }

    @PutMany(SummonerSpell.class)
    public void putManySummonerSpell(final Iterable<SummonerSpell> spells, final PipelineContext context) {
        for(final SummonerSpell spell : spells) {
            putSummonerSpell(spell, context);
        }
    }

    @Put(Maps.class)
    public void putMaps(final Maps maps, final PipelineContext context) {
        final int key = UniqueKeys.forMaps(maps);
        cache.put(key, maps);
    }

    @Put(Masteries.class)
    public void putMasteries(final Masteries masteries, final PipelineContext context) {
        final int key = UniqueKeys.forMasteries(masteries);
        cache.put(key, masteries);

        if(masteries.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putMasteries(masteries, null);
                }
            };

            masteries.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyMastery(masteries, context);
        }
    }

    @Put(Mastery.class)
    public void putMastery(final Mastery item, final PipelineContext context) {
        final int[] keys = UniqueKeys.forMastery(item);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putMastery(item, null);
                }
            };

            item.registerGhostLoadHook(hook, Mastery.MASTERY_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, item);
        }
    }

    @Put(ProfileIcon.class)
    public void putProfileIcon(final ProfileIcon profileIcon, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIcon(profileIcon);
        cache.put(key, profileIcon);
    }

    @Put(ProfileIcons.class)
    public void putProfileIcons(final ProfileIcons icons, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIcons(icons);
        cache.put(key, icons);

        if(icons.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putProfileIcons(icons, null);
                }
            };

            icons.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyProfileIcon(icons, context);
        }
    }

    @Put(Realm.class)
    public void putRealm(final Realm realm, final PipelineContext context) {
        final int key = UniqueKeys.forRealm(realm);
        cache.put(key, realm);
    }

    @Put(Rune.class)
    public void putRune(final Rune rune, final PipelineContext context) {
        final int[] keys = UniqueKeys.forRune(rune);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putRune(rune, null);
                }
            };

            rune.registerGhostLoadHook(hook, Rune.RUNE_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, rune);
        }
    }

    @Put(Runes.class)
    public void putRunes(final Runes runes, final PipelineContext context) {
        final int key = UniqueKeys.forRunes(runes);
        cache.put(key, runes);

        if(runes.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putRunes(runes, null);
                }
            };

            runes.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyRune(runes, context);
        }
    }

    @Put(Summoner.class)
    public void putSummoner(final Summoner summoner, final PipelineContext context) {
        final int[] keys = UniqueKeys.forSummoner(summoner);

        if(keys.length < 3) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putSummoner(summoner, null);
                }
            };

            summoner.registerGhostLoadHook(hook, Summoner.SUMMONER_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, summoner);
        }
    }

    @Put(SummonerSpell.class)
    public void putSummonerSpell(final SummonerSpell summonerSpell, final PipelineContext context) {
        final int[] keys = UniqueKeys.forSummonerSpell(summonerSpell);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putSummonerSpell(summonerSpell, null);
                }
            };

            summonerSpell.registerGhostLoadHook(hook, SummonerSpell.SUMMONER_SPELL_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, summonerSpell);
        }
    }

    @Put(SummonerSpells.class)
    public void putSummonerSpells(final SummonerSpells spells, final PipelineContext context) {
        final int key = UniqueKeys.forSummonerSpells(spells);
        cache.put(key, spells);

        if(spells.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putSummonerSpells(spells, null);
                }
            };

            spells.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManySummonerSpell(spells, context);
        }
    }
}
