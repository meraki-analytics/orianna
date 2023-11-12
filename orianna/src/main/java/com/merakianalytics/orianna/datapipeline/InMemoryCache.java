package com.merakianalytics.orianna.datapipeline;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.merakianalytics.orianna.types.core.account.Account;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.cache2k.CacheEntry;
import org.cache2k.expiry.ExpiryPolicy;
import org.cache2k.expiry.ExpiryTimeValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
import com.merakianalytics.orianna.types.core.champion.ChampionRotation;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.Timeline;
import com.merakianalytics.orianna.types.core.match.TournamentMatches;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
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
import com.merakianalytics.orianna.types.core.staticdata.Patch;
import com.merakianalytics.orianna.types.core.staticdata.Patches;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRune;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRunes;
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
        private static final long DEFAULT_EXPIRATION_PERIOD_MAX = 6L;
        private static final TimeUnit DEFAULT_EXPIRATION_PERIOD_UNIT_MAX = TimeUnit.HOURS;
        private static final java.util.Map<String, ExpirationPeriod> DEFAULT_EXPIRATION_PERIODS = ImmutableMap.<String, ExpirationPeriod> builder()
            .put(Account.class.getCanonicalName(), ExpirationPeriod.create(1L, TimeUnit.HOURS))
            .put(ChampionRotation.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(ChampionMastery.class.getCanonicalName(), ExpirationPeriod.create(15L, TimeUnit.MINUTES))
            .put(ChampionMasteries.class.getCanonicalName(), ExpirationPeriod.create(15L, TimeUnit.MINUTES))
            .put(ChampionMasteryScore.class.getCanonicalName(), ExpirationPeriod.create(15L, TimeUnit.MINUTES))
            .put(Champion.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Champions.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(CurrentMatch.class.getCanonicalName(), ExpirationPeriod.create(5L, TimeUnit.MINUTES))
            .put(FeaturedMatches.class.getCanonicalName(), ExpirationPeriod.create(5L, TimeUnit.MINUTES))
            .put(Item.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Items.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(LanguageStrings.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(Languages.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(League.class.getCanonicalName(), ExpirationPeriod.create(15L, TimeUnit.MINUTES))
            .put(LeaguePositions.class.getCanonicalName(), ExpirationPeriod.create(15L, TimeUnit.MINUTES))
            .put(Map.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Maps.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Mastery.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Masteries.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Match.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            // TODO: MatchHistory
            .put(Patch.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Patches.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(ProfileIcon.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(ProfileIcons.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Realm.class.getCanonicalName(), ExpirationPeriod.create(6L, TimeUnit.HOURS))
            .put(ReforgedRune.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(ReforgedRunes.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Rune.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Runes.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(ShardStatus.class.getCanonicalName(), ExpirationPeriod.create(15L, TimeUnit.MINUTES))
            .put(SummonerSpell.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(SummonerSpells.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(Summoner.class.getCanonicalName(), ExpirationPeriod.create(1L, TimeUnit.HOURS))
            .put(Timeline.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
            .put(TournamentMatches.class.getCanonicalName(), ExpirationPeriod.create(DEFAULT_EXPIRATION_PERIOD_MAX, DEFAULT_EXPIRATION_PERIOD_UNIT_MAX))
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
            final Long period = expirationPeriods.get(value.getClass());
            if(period != null && period > 0L) {
                return loadTime + period.longValue();
            }
            return ExpiryTimeValues.ETERNAL;
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

        cache = new Cache2kBuilder<Integer, Object>() {}.disableStatistics(true).expiryPolicy(new Policy()).keepDataAfterExpired(false).permitNullValues(false)
            .storeByReference(true).build();
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

    @Get(ChampionRotation.class)
    public ChampionRotation getChampionRotation(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionRotationQuery(query);
        return (ChampionRotation)cache.get(key);
    }

    @Get(Champions.class)
    public Champions getChampions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionsQuery(query);
        return (Champions)cache.get(key);
    }

    @Get(CurrentMatch.class)
    public CurrentMatch getCurrentMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forCurrentMatchQuery(query);
        return (CurrentMatch)cache.get(key);
    }

    @Get(FeaturedMatches.class)
    public FeaturedMatches getFeaturedMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forFeaturedMatchesQuery(query);
        return (FeaturedMatches)cache.get(key);
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

    @GetMany(ChampionRotation.class)
    public CloseableIterator<ChampionRotation> getManyChampionRotation(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyChampionRotationQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<ChampionRotation>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionRotation next() {
                final int key = iterator.next();
                return (ChampionRotation)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(CurrentMatch.class)
    public CloseableIterator<CurrentMatch> getManyCurrentMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyCurrentMatchQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<CurrentMatch>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public CurrentMatch next() {
                final int key = iterator.next();
                return (CurrentMatch)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(FeaturedMatches.class)
    public CloseableIterator<FeaturedMatches> getManyFeaturedMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyFeaturedMatchesQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<FeaturedMatches>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public FeaturedMatches next() {
                final int key = iterator.next();
                return (FeaturedMatches)cache.get(key);
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

    @GetMany(Match.class)
    public CloseableIterator<Match> getManyMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyMatchQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Match>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Match next() {
                final int key = iterator.next();
                return (Match)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(Patch.class)
    public CloseableIterator<Patch> getManyPatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyPatchQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Patch>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Patch next() {
                final int key = iterator.next();
                return (Patch)cache.get(key);
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

    @GetMany(Realm.class)
    public CloseableIterator<Realm> getManyRealm(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyRealmQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Realm>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Realm next() {
                final int key = iterator.next();
                return (Realm)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(ReforgedRune.class)
    public CloseableIterator<ReforgedRune> getManyReforgedRune(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyReforgedRuneQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<ReforgedRune>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ReforgedRune next() {
                final int key = iterator.next();
                return (ReforgedRune)cache.get(key);
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

    @GetMany(Timeline.class)
    public CloseableIterator<Timeline> getManyTimeline(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyTimelineQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Timeline>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Timeline next() {
                final int key = iterator.next();
                return (Timeline)cache.get(key);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @GetMany(TournamentMatches.class)
    public CloseableIterator<TournamentMatches> getManyTournamentMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyTournamentMatchesQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<TournamentMatches>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public TournamentMatches next() {
                final int key = iterator.next();
                return (TournamentMatches)cache.get(key);
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

    @GetMany(Versions.class)
    public CloseableIterator<Versions> getManyVersions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final List<Integer> keys = Lists.newArrayList(UniqueKeys.forManyVersionsQuery(query));
        for(final Integer key : keys) {
            if(!cache.containsKey(key)) {
                return null;
            }
        }

        final Iterator<Integer> iterator = keys.iterator();
        return CloseableIterators.from(new Iterator<Versions>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Versions next() {
                final int key = iterator.next();
                return (Versions)cache.get(key);
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

    @Get(Match.class)
    public Match getMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMatchQuery(query);
        return (Match)cache.get(key);
    }

    @Get(Patch.class)
    public Patch getPatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forPatchQuery(query);
        return (Patch)cache.get(key);
    }

    @Get(Patches.class)
    public Patches getPatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forPatchesQuery(query);
        return (Patches)cache.get(key);
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

    @Get(ReforgedRune.class)
    public ReforgedRune getReforgedRune(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forReforgedRuneQuery(query);
        return (ReforgedRune)cache.get(key);
    }

    @Get(ReforgedRunes.class)
    public ReforgedRunes getReforgedRunes(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forReforgedRunesQuery(query);
        return (ReforgedRunes)cache.get(key);
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

    @Get(Timeline.class)
    public Timeline getTimeline(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forTimelineQuery(query);
        return (Timeline)cache.get(key);
    }

    @Get(TournamentMatches.class)
    public TournamentMatches getTournamentMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forTournamentMatchesQuery(query);
        return (TournamentMatches)cache.get(key);
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

    @Override
    protected Set<Class<?>> ignore() {
        final Set<String> included = new HashSet<>();
        for(final Class<?> clazz : expirationPeriods.keySet()) {
            if(expirationPeriods.get(clazz) != 0L) {
                included.add(clazz.getCanonicalName());
            }
        }

        final Set<String> names = Sets.difference(Configuration.DEFAULT_EXPIRATION_PERIODS.keySet(), included);
        if(names.isEmpty()) {
            return Collections.emptySet();
        }

        final Set<Class<?>> ignore = new HashSet<>();
        for(final String name : names) {
            try {
                ignore.add(Class.forName(name));
            } catch(final ClassNotFoundException e) {
                LOGGER.error("Failed to find class for name " + name + "!", e);
                throw new OriannaException("Failed to find class for name " + name + "! Report this to the orianna team.", e);
            }
        }
        return ignore;
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

    @Put(ChampionRotation.class)
    public void putChampionRotation(final ChampionRotation rotation, final PipelineContext context) {
        final int key = UniqueKeys.forChampionRotation(rotation);
        cache.put(key, rotation);
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

    @Put(CurrentMatch.class)
    public void putCurrentMatch(final CurrentMatch game, final PipelineContext context) {
        final int key = UniqueKeys.forCurrentMatch(game);
        cache.put(key, game);
    }

    @Put(FeaturedMatches.class)
    public void putFeaturedMatches(final FeaturedMatches games, final PipelineContext context) {
        final int key = UniqueKeys.forFeaturedMatches(games);
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

    @PutMany(ChampionRotation.class)
    public void putManyChampionRotation(final Iterable<ChampionRotation> rotations, final PipelineContext context) {
        for(final ChampionRotation rotation : rotations) {
            putChampionRotation(rotation, context);
        }
    }

    @PutMany(CurrentMatch.class)
    public void putManyCurrentMatch(final Iterable<CurrentMatch> games, final PipelineContext context) {
        for(final CurrentMatch game : games) {
            putCurrentMatch(game, context);
        }
    }

    @PutMany(FeaturedMatches.class)
    public void putManyFeaturedMatches(final Iterable<FeaturedMatches> games, final PipelineContext context) {
        for(final FeaturedMatches game : games) {
            putFeaturedMatches(game, context);
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

    @PutMany(Match.class)
    public void putManyMatch(final Iterable<Match> matches, final PipelineContext context) {
        for(final Match match : matches) {
            putMatch(match, context);
        }
    }

    @PutMany(Patch.class)
    public void putManyPatch(final Iterable<Patch> patches, final PipelineContext context) {
        for(final Patch patch : patches) {
            putPatch(patch, context);
        }
    }

    @PutMany(ProfileIcon.class)
    public void putManyProfileIcon(final Iterable<ProfileIcon> icons, final PipelineContext context) {
        for(final ProfileIcon icon : icons) {
            putProfileIcon(icon, context);
        }
    }

    @PutMany(Realm.class)
    public void putManyRealm(final Iterable<Realm> realms, final PipelineContext context) {
        for(final Realm realm : realms) {
            putRealm(realm, context);
        }
    }

    @PutMany(ReforgedRune.class)
    public void putManyReforgedRune(final Iterable<ReforgedRune> runes, final PipelineContext context) {
        for(final ReforgedRune rune : runes) {
            putReforgedRune(rune, context);
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

    @PutMany(Account.class)
    public void putManyAccount(final Iterable<Account> accounts, final PipelineContext context) {
        for (final Account account : accounts) {
            putAccount(account, context);
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

    @PutMany(Timeline.class)
    public void putManyTimeline(final Iterable<Timeline> timelines, final PipelineContext context) {
        for(final Timeline timeline : timelines) {
            putTimeline(timeline, context);
        }
    }

    @PutMany(TournamentMatches.class)
    public void putManyTournamentMatches(final Iterable<TournamentMatches> matches, final PipelineContext context) {
        for(final TournamentMatches match : matches) {
            putTournamentMatches(match, context);
        }
    }

    @PutMany(VerificationString.class)
    public void putManyVerificationString(final Iterable<VerificationString> strings, final PipelineContext context) {
        for(final VerificationString string : strings) {
            putVerificationString(string, context);
        }
    }

    @PutMany(Versions.class)
    public void putManyVersions(final Iterable<Versions> versions, final PipelineContext context) {
        for(final Versions version : versions) {
            putVersions(version, context);
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

    @Put(Match.class)
    public void putMatch(final Match match, final PipelineContext context) {
        final int key = UniqueKeys.forMatch(match);
        cache.put(key, match);
    }

    @Put(Patch.class)
    public void putPatch(final Patch patch, final PipelineContext context) {
        final int key = UniqueKeys.forPatch(patch);

        if(patch.getCoreData().getName() == null) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putPatch(patch, null);
                }
            };

            patch.registerGhostLoadHook(hook, Patch.PATCH_LOAD_GROUP);
        }

        cache.put(key, patch);
    }

    @Put(Patches.class)
    public void putPatches(final Patches patches, final PipelineContext context) {
        final int key = UniqueKeys.forPatches(patches);
        cache.put(key, patches);

        if(patches.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putPatches(patches, null);
                }
            };

            patches.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyPatch(patches, context);
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

    @Put(ReforgedRune.class)
    public void putReforgedRune(final ReforgedRune rune, final PipelineContext context) {
        final int[] keys = UniqueKeys.forReforgedRune(rune);

        if(keys.length < 3) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putReforgedRune(rune, null);
                }
            };

            rune.registerGhostLoadHook(hook, ReforgedRune.REFORGED_RUNE_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, rune);
        }
    }

    @Put(ReforgedRunes.class)
    public void putReforgedRunes(final ReforgedRunes runes, final PipelineContext context) {
        final int key = UniqueKeys.forReforgedRunes(runes);
        cache.put(key, runes);

        if(runes.getCoreData().isEmpty()) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putReforgedRunes(runes, null);
                }
            };

            runes.registerGhostLoadHook(hook, ListProxy.LIST_PROXY_LOAD_GROUP);
        } else {
            putManyReforgedRune(runes, context);
        }
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

    @Put(Account.class)
    public void putAccount(final Account account, final PipelineContext context) {
        final int[] keys = UniqueKeys.forAccount(account);

        if (keys.length > 1) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putAccount(account, null);
                }
            };

            account.registerGhostLoadHook(hook, Account.ACCOUNT_LOAD_GROUP);

            for(final int key : keys) {
                cache.put(key, account);
            }
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

    @Put(Timeline.class)
    public void putTimeline(final Timeline timeline, final PipelineContext context) {
        final int key = UniqueKeys.forTimeline(timeline);
        cache.put(key, timeline);
    }

    @Put(TournamentMatches.class)
    public void putTournamentMatches(final TournamentMatches matches, final PipelineContext context) {
        final int key = UniqueKeys.forTournamentMatches(matches);
        cache.put(key, matches);
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
