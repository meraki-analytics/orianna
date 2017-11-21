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
import com.merakianalytics.orianna.types.UniqueKeys;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.core.GhostObject.LoadHook;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class InMemoryCache extends AbstractDataStore {
    public static class Configuration {
        private static final Map<String, ExpirationPeriod> DEFAULT_EXPIRATION_PERIODS = ImmutableMap.<String, ExpirationPeriod> builder()
            .put(Champion.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Item.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(LanguageStrings.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Languages.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Maps.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Mastery.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(ProfileIcons.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Realm.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Rune.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(SummonerSpell.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Versions.class.getCanonicalName(), period(6, TimeUnit.HOURS))
            .put(Summoner.class.getCanonicalName(), period(30, TimeUnit.MINUTES))
            .build();

        private static ExpirationPeriod period(final long time, final TimeUnit unit) {
            final ExpirationPeriod period = new ExpirationPeriod();
            period.setPeriod(time);
            period.setUnit(unit);
            return period;
        }

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

    public static class ExpirationPeriod {
        private long period;
        private TimeUnit unit;

        /**
         * @return the period
         */
        public long getPeriod() {
            return period;
        }

        /**
         * @return the unit
         */
        public TimeUnit getUnit() {
            return unit;
        }

        /**
         * @param period
         *        the period to set
         */
        public void setPeriod(final long period) {
            this.period = period;
        }

        /**
         * @param unit
         *        the unit to set
         */
        public void setUnit(final TimeUnit unit) {
            this.unit = unit;
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

    @Get(Maps.class)
    public Maps getMaps(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMapsQuery(query);
        return (Maps)cache.get(key);
    }

    @Get(Mastery.class)
    public Mastery getMastery(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMasteryQuery(query);
        return (Mastery)cache.get(key);
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

    @Get(Versions.class)
    public Versions getVersions(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forVersionsQuery(query);
        return (Versions)cache.get(key);
    }

    @Put(Champion.class)
    public void putChampion(final Champion champion, final PipelineContext context) {
        final int[] keys = UniqueKeys.forChampion(champion);

        if(keys.length < 2) {
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

    @PutMany(Summoner.class)
    public void putManySummoner(final Iterable<Summoner> summoners, final PipelineContext context) {
        for(final Summoner summoner : summoners) {
            putSummoner(summoner, context);
        }
    }

    @Put(Maps.class)
    public void putMaps(final Maps maps, final PipelineContext context) {
        final int key = UniqueKeys.forMaps(maps);
        cache.put(key, maps);
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

    @Put(ProfileIcons.class)
    public void putProfileIcons(final ProfileIcons profileIcons, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIcons(profileIcons);
        cache.put(key, profileIcons);
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
}
