package com.merakianalytics.orianna.datapipeline;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.cache2k.CacheEntry;
import org.cache2k.expiry.ExpiryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
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
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.GhostObject.ListProxy;
import com.merakianalytics.orianna.types.core.GhostObject.LoadHook;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.spectator.CurrentGame;
import com.merakianalytics.orianna.types.core.spectator.FeaturedGames;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.Items;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Map;
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
import com.merakianalytics.orianna.types.core.status.ShardStatus;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.thirdpartycode.VerificationString;

public class InMemoryCache extends AbstractDataStore {
    public static class Configuration {
        private static final java.util.Map<String, ExpirationPeriod> DEFAULT_EXPIRATION_PERIODS = ImmutableMap.<String, ExpirationPeriod> builder()
            .put(ChampionMastery.class.getCanonicalName(), ExpirationPeriod.create(30L, TimeUnit.MINUTES))
            .put(ChampionMasteries.class.getCanonicalName(), ExpirationPeriod.create(30L, TimeUnit.MINUTES))
            .put(ChampionMasteryScore.class.getCanonicalName(), ExpirationPeriod.create(30L, TimeUnit.MINUTES))
            .put(Champion.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Champions.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(CurrentGame.class.getCanonicalName(), ExpirationPeriod.create(5L, TimeUnit.MINUTES))
            .put(FeaturedGames.class.getCanonicalName(), ExpirationPeriod.create(5L, TimeUnit.MINUTES))
            .put(Item.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Items.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(LanguageStrings.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Languages.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(League.class.getCanonicalName(), ExpirationPeriod.create(30, TimeUnit.MINUTES))
            .put(LeaguePositions.class.getCanonicalName(), ExpirationPeriod.create(30L, TimeUnit.MINUTES))
            .put(Map.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Maps.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Mastery.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Masteries.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(ProfileIcon.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(ProfileIcons.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Realm.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Rune.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Runes.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(ShardStatus.class.getCanonicalName(), ExpirationPeriod.create(15L, TimeUnit.MINUTES))
            .put(SummonerSpell.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(SummonerSpells.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Summoner.class.getCanonicalName(), ExpirationPeriod.create(30L, TimeUnit.MINUTES))
            .put(VerificationString.class.getCanonicalName(), ExpirationPeriod.create(3L, TimeUnit.MINUTES))
            .put(Versions.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .build();

        private java.util.Map<String, ExpirationPeriod> expirationPeriods = DEFAULT_EXPIRATION_PERIODS;

        /**
         * @return the expirationPeriods
         */
        public java.util.Map<String, ExpirationPeriod> getExpirationPeriods() {
            return expirationPeriods;
        }

        /**
         * @param expirationPeriods
         *        the expirationPeriods to set
         */
        public void setExpirationPeriods(final java.util.Map<String, ExpirationPeriod> expirationPeriods) {
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
    private static final Set<Tier> UNIQUE_TIERS = ImmutableSet.of(Tier.CHALLENGER, Tier.MASTER);
    private final Cache<Integer, Object> cache;
    private final java.util.Map<Class<?>, Long> expirationPeriods;

    public InMemoryCache() {
        this(new Configuration());
    }

    public InMemoryCache(final Configuration config) {
        final java.util.Map<Class<?>, Long> periods = new HashMap<>();

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
    public Champion getChampion(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionQuery(query);
        return (Champion)cache.get(key);
    }

    @Get(ChampionMasteries.class)
    public ChampionMasteries getChampionMasteries(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionMasteriesQuery(query);
        return (ChampionMasteries)cache.get(key);
    }

    @Get(ChampionMastery.class)
    public ChampionMastery getChampionMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionMasteryQuery(query);
        return (ChampionMastery)cache.get(key);
    }

    @Get(ChampionMasteryScore.class)
    public ChampionMasteryScore getChampionMasteryScore(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionMasteryScoreQuery(query);
        return (ChampionMasteryScore)cache.get(key);
    }

    @Get(Champions.class)
    public Champions getChampions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionsQuery(query);
        return (Champions)cache.get(key);
    }

    @Get(CurrentGame.class)
    public CurrentGame getCurrentGame(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forCurrentGameQuery(query);
        return (CurrentGame)cache.get(key);
    }

    @Get(FeaturedGames.class)
    public FeaturedGames getFeaturedGames(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forFeaturedGamesQuery(query);
        return (FeaturedGames)cache.get(key);
    }

    @Get(Item.class)
    public Item getItem(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forItemQuery(query);
        return (Item)cache.get(key);
    }

    @Get(Items.class)
    public Items getItems(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forItemsQuery(query);
        return (Items)cache.get(key);
    }

    @Get(Languages.class)
    public Languages getLanguages(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLanguagesQuery(query);
        return (Languages)cache.get(key);
    }

    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLanguageStringsQuery(query);
        return (LanguageStrings)cache.get(key);
    }

    @Get(League.class)
    public League getLeague(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLeagueQuery(query);
        return (League)cache.get(key);
    }

    @Get(LeaguePositions.class)
    public LeaguePositions getLeaguePositions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLeaguePositionsQuery(query);
        return (LeaguePositions)cache.get(key);
    }

    @GetMany(Champion.class)
    public CloseableIterator<Champion> getManyChampion(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyChampionQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Champion>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Champion next() {
                final int key = iterator.next();
                return (Champion)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(ChampionMasteries.class)
    public CloseableIterator<ChampionMasteries> getManyChampionMasteries(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyChampionMasteriesQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<ChampionMasteries>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMasteries next() {
                final int key = iterator.next();
                return (ChampionMasteries)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(ChampionMastery.class)
    public CloseableIterator<ChampionMastery> getManyChampionMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyChampionMasteryQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<ChampionMastery>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMastery next() {
                final int key = iterator.next();
                return (ChampionMastery)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(ChampionMasteryScore.class)
    public CloseableIterator<ChampionMasteryScore> getManyChampionMasteryScore(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyChampionMasteryScoreQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<ChampionMasteryScore>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMasteryScore next() {
                final int key = iterator.next();
                return (ChampionMasteryScore)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(CurrentGame.class)
    public CloseableIterator<CurrentGame> getManyCurrentGame(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyCurrentGameQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<CurrentGame>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public CurrentGame next() {
                final int key = iterator.next();
                return (CurrentGame)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(FeaturedGames.class)
    public CloseableIterator<FeaturedGames> getManyFeaturedGames(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyFeaturedGamesQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<FeaturedGames>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public FeaturedGames next() {
                final int key = iterator.next();
                return (FeaturedGames)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Item.class)
    public CloseableIterator<Item> getManyItem(final java.util.Map<String, Object> query, final PipelineContext context) {
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

    @GetMany(League.class)
    public CloseableIterator<League> getManyLeague(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyLeagueQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<League>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public League next() {
                final int key = iterator.next();
                return (League)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(LeaguePositions.class)
    public CloseableIterator<LeaguePositions> getManyLeaguePositions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyLeaguePositionsQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<LeaguePositions>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public LeaguePositions next() {
                final int key = iterator.next();
                return (LeaguePositions)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Map.class)
    public CloseableIterator<Map> getManyMap(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyMapQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Map>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Map next() {
                final int key = iterator.next();
                return (Map)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Mastery.class)
    public CloseableIterator<Mastery> getManyMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
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
    public CloseableIterator<ProfileIcon> getManyProfileIcon(final java.util.Map<String, Object> query, final PipelineContext context) {
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
    public CloseableIterator<Rune> getManyRune(final java.util.Map<String, Object> query, final PipelineContext context) {
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

    @GetMany(ShardStatus.class)
    public CloseableIterator<ShardStatus> getManyShardStatus(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyShardStatusQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<ShardStatus>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ShardStatus next() {
                final int key = iterator.next();
                return (ShardStatus)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Summoner.class)
    public CloseableIterator<Summoner> getManySummoner(final java.util.Map<String, Object> query, final PipelineContext context) {
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
    public CloseableIterator<SummonerSpell> getManySummonerSpell(final java.util.Map<String, Object> query, final PipelineContext context) {
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

    @GetMany(VerificationString.class)
    public CloseableIterator<VerificationString> getManyVerificationString(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyVerificationStringQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<VerificationString>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public VerificationString next() {
                final int key = iterator.next();
                return (VerificationString)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(Map.class)
    public Map getMap(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMapQuery(query);
        return (Map)cache.get(key);
    }

    @Get(Maps.class)
    public Maps getMaps(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMapsQuery(query);
        return (Maps)cache.get(key);
    }

    @Get(Masteries.class)
    public Masteries getMasteries(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMasteriesQuery(query);
        return (Masteries)cache.get(key);
    }

    @Get(Mastery.class)
    public Mastery getMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMasteryQuery(query);
        return (Mastery)cache.get(key);
    }

    @Get(ProfileIcon.class)
    public ProfileIcon getProfileIcon(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIconQuery(query);
        return (ProfileIcon)cache.get(key);
    }

    @Get(ProfileIcons.class)
    public ProfileIcons getProfileIcons(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIconsQuery(query);
        return (ProfileIcons)cache.get(key);
    }

    @Get(Realm.class)
    public Realm getRealm(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forRealmQuery(query);
        return (Realm)cache.get(key);
    }

    @Get(Rune.class)
    public Rune getRune(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forRuneQuery(query);
        return (Rune)cache.get(key);
    }

    @Get(Runes.class)
    public Runes getRunes(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forRunesQuery(query);
        return (Runes)cache.get(key);
    }

    @Get(ShardStatus.class)
    public ShardStatus getShardStatus(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forShardStatusQuery(query);
        return (ShardStatus)cache.get(key);
    }

    @Get(Summoner.class)
    public Summoner getSummoner(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forSummonerQuery(query);
        return (Summoner)cache.get(key);
    }

    @Get(SummonerSpell.class)
    public SummonerSpell getSummonerSpell(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forSummonerSpellQuery(query);
        return (SummonerSpell)cache.get(key);
    }

    @Get(SummonerSpells.class)
    public SummonerSpells getSummonerSpells(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forSummonerSpellsQuery(query);
        return (SummonerSpells)cache.get(key);
    }

    @Get(VerificationString.class)
    public VerificationString getVerificationString(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forVerificationStringQuery(query);
        return (VerificationString)cache.get(key);
    }

    @Get(Versions.class)
    public Versions getVersions(final java.util.Map<String, Object> query, final PipelineContext context) {
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

    @Put(ChampionMasteries.class)
    public void putChampionMasteries(final ChampionMasteries masteries, final PipelineContext context) {
        final int key = UniqueKeys.forChampionMasteries(masteries);
        cache.put(key, masteries);

        if(masteries.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putChampionMasteries(masteries, null);
                }
            };

            masteries.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyChampionMastery(masteries, context);
        }
    }

    @Put(ChampionMastery.class)
    public void putChampionMastery(final ChampionMastery mastery, final PipelineContext context) {
        final int key = UniqueKeys.forChampionMastery(mastery);
        cache.put(key, mastery);
    }

    @Put(ChampionMasteryScore.class)
    public void putChampionMasteryScore(final ChampionMasteryScore score, final PipelineContext context) {
        final int key = UniqueKeys.forChampionMasteryScore(score);
        cache.put(key, score);
    }

    @Put(Champions.class)
    public void putChampions(final Champions champions, final PipelineContext context) {
        final int key = UniqueKeys.forChampions(champions);
        cache.put(key, champions);

        if(champions.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putChampions(champions, null);
                }
            };

            champions.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyChampion(champions, context);
        }
    }

    @Put(CurrentGame.class)
    public void putCurrentGame(final CurrentGame game, final PipelineContext context) {
        final int key = UniqueKeys.forCurrentGame(game);
        cache.put(key, game);
    }

    @Put(FeaturedGames.class)
    public void putFeaturedGames(final FeaturedGames games, final PipelineContext context) {
        final int key = UniqueKeys.forFeaturedGames(games);
        cache.put(key, games);
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

    @Put(LanguageStrings.class)
    public void putLanguageStrings(final LanguageStrings languageStrings, final PipelineContext context) {
        final int key = UniqueKeys.forLanguageStrings(languageStrings);
        cache.put(key, languageStrings);
    }

    @Put(League.class)
    public void putLeague(final League league, final PipelineContext context) {
        final int[] keys = UniqueKeys.forLeague(league);

        if(keys.length < 2 && league.getCoreData().getTier() == null || league.getCoreData().getQueue() != null
            || Queue.RANKED.contains(league.getQueue()) && UNIQUE_TIERS.contains(league.getTier())) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putLeague(league, null);
                }
            };

            league.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, league);
        }
    }

    @Put(LeaguePositions.class)
    public void putLeaguePositions(final LeaguePositions positions, final PipelineContext context) {
        final int key = UniqueKeys.forLeaguePositions(positions);
        cache.put(key, positions);
    }

    @PutMany(Champion.class)
    public void putManyChampion(final Iterable<Champion> champions, final PipelineContext context) {
        for(final Champion champion : champions) {
            putChampion(champion, context);
        }
    }

    @PutMany(ChampionMasteries.class)
    public void putManyChampionMasteries(final Iterable<ChampionMasteries> masteries, final PipelineContext context) {
        for(final ChampionMasteries mastery : masteries) {
            putChampionMasteries(mastery, context);
        }
    }

    @PutMany(ChampionMastery.class)
    public void putManyChampionMastery(final Iterable<ChampionMastery> masteries, final PipelineContext context) {
        for(final ChampionMastery mastery : masteries) {
            putChampionMastery(mastery, context);
        }
    }

    @PutMany(ChampionMasteryScore.class)
    public void putManyChampionMasteryScore(final Iterable<ChampionMasteryScore> scores, final PipelineContext context) {
        for(final ChampionMasteryScore score : scores) {
            putChampionMasteryScore(score, context);
        }
    }

    @PutMany(CurrentGame.class)
    public void putManyCurrentGame(final Iterable<CurrentGame> games, final PipelineContext context) {
        for(final CurrentGame game : games) {
            putCurrentGame(game, context);
        }
    }

    @PutMany(FeaturedGames.class)
    public void putManyFeaturedGames(final Iterable<FeaturedGames> games, final PipelineContext context) {
        for(final FeaturedGames game : games) {
            putFeaturedGames(game, context);
        }
    }

    @PutMany(Item.class)
    public void putManyItem(final Iterable<Item> items, final PipelineContext context) {
        for(final Item item : items) {
            putItem(item, context);
        }
    }

    @PutMany(League.class)
    public void putManyLeague(final Iterable<League> leagues, final PipelineContext context) {
        for(final League league : leagues) {
            putLeague(league, context);
        }
    }

    @PutMany(LeaguePositions.class)
    public void putManyLeaguePositions(final Iterable<LeaguePositions> positions, final PipelineContext context) {
        for(final LeaguePositions position : positions) {
            putLeaguePositions(position, context);
        }
    }

    @PutMany(Map.class)
    public void putManyMap(final Iterable<Map> maps, final PipelineContext context) {
        for(final Map map : maps) {
            putMap(map, context);
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

    @PutMany(ShardStatus.class)
    public void putManyShardStatus(final Iterable<ShardStatus> statuses, final PipelineContext context) {
        for(final ShardStatus status : statuses) {
            putShardStatus(status, context);
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

    @PutMany(VerificationString.class)
    public void putManyVerificationString(final Iterable<VerificationString> strings, final PipelineContext context) {
        for(final VerificationString string : strings) {
            putVerificationString(string, context);
        }
    }

    @Put(Map.class)
    public void putMap(final Map map, final PipelineContext context) {
        final int[] keys = UniqueKeys.forMap(map);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putMap(map, null);
                }
            };

            map.registerGhostLoadHook(hook, Map.MAP_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, map);
        }
    }

    @Put(Maps.class)
    public void putMaps(final Maps maps, final PipelineContext context) {
        final int key = UniqueKeys.forMaps(maps);
        cache.put(key, maps);

        if(maps.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putMaps(maps, null);
                }
            };

            maps.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyMap(maps, context);
        }
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
    public void putMastery(final Mastery mastery, final PipelineContext context) {
        final int[] keys = UniqueKeys.forMastery(mastery);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putMastery(mastery, null);
                }
            };

            mastery.registerGhostLoadHook(hook, Mastery.MASTERY_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, mastery);
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

    @Put(ShardStatus.class)
    public void putShardStatus(final ShardStatus status, final PipelineContext context) {
        final int key = UniqueKeys.forShardStatus(status);
        cache.put(key, status);
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

    @Put(VerificationString.class)
    public void putVerificationString(final VerificationString string, final PipelineContext context) {
        final int key = UniqueKeys.forVerificationString(string);
        cache.put(key, string);
    }

    @Put(Versions.class)
    public void putVersions(final Versions versions, final PipelineContext context) {
        final int key = UniqueKeys.forVersions(versions);
        cache.put(key, versions);
    }
}
