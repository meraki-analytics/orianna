package com.merakianalytics.orianna.datapipeline;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.QueryValidationException;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.champion.ChampionRotation;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
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

public class GhostObjectSource extends AbstractDataSource {
    private static final Set<Tier> UNIQUE_TIERS = ImmutableSet.of(Tier.CHALLENGER, Tier.MASTER);

    private static String getCurrentVersion(final Platform platform, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Realm realm =
            context.getPipeline().get(com.merakianalytics.orianna.types.dto.staticdata.Realm.class, ImmutableMap.<String, Object> of("platform", platform));
        return realm.getV();
    }

    @SuppressWarnings("unchecked")
    @Get(Champion.class)
    public Champion getChampion(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        final String key = (String)query.get("key");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name", key, "key");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Champion data = new com.merakianalytics.orianna.types.data.staticdata.Champion();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setKey(key);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Champion(data);
    }

    @Get(ChampionMasteries.class)
    public ChampionMasteries getChampionMasteries(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries data =
            new com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries();
        data.setPlatform(platform.getTag());
        data.setSummonerId(summonerId);
        return new ChampionMasteries(data);
    }

    @Get(ChampionMastery.class)
    public ChampionMastery getChampionMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        final Number championId = (Number)query.get("championId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId", championId, "championId");

        final com.merakianalytics.orianna.types.data.championmastery.ChampionMastery data =
            new com.merakianalytics.orianna.types.data.championmastery.ChampionMastery();
        data.setPlatform(platform.getTag());
        data.setSummonerId(summonerId);
        data.setChampionId(championId.intValue());
        return new ChampionMastery(data);
    }

    @Get(ChampionMasteryScore.class)
    public ChampionMasteryScore getChampionMasteryScore(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore data =
            new com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore();
        data.setPlatform(platform.getTag());
        data.setSummonerId(summonerId);
        return new ChampionMasteryScore(data);
    }

    @Get(ChampionRotation.class)
    public ChampionRotation getChampionRotation(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.champion.ChampionRotation data = new com.merakianalytics.orianna.types.data.champion.ChampionRotation();
        data.setPlatform(platform.getTag());
        return new ChampionRotation(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Champions.class)
    public Champions getChampions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Champions data = new com.merakianalytics.orianna.types.data.staticdata.Champions();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Champions(data);
    }

    @Get(CurrentMatch.class)
    public CurrentMatch getCurrentMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number summonerId = (Number)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final com.merakianalytics.orianna.types.data.spectator.CurrentMatch data =
            new com.merakianalytics.orianna.types.data.spectator.CurrentMatch();
        data.setPlatform(platform.getTag());
        data.setSummonerId(summonerId.longValue());
        return new CurrentMatch(data);
    }

    @Get(FeaturedMatches.class)
    public FeaturedMatches getFeaturedMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.spectator.FeaturedMatches data = new com.merakianalytics.orianna.types.data.spectator.FeaturedMatches();
        data.setPlatform(platform.getTag());
        return new FeaturedMatches(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Item.class)
    public Item getItem(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Item data = new com.merakianalytics.orianna.types.data.staticdata.Item();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Item(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Items.class)
    public Items getItems(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Items data = new com.merakianalytics.orianna.types.data.staticdata.Items();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Items(data);
    }

    @Get(Languages.class)
    public Languages getLanguages(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.staticdata.Languages data = new com.merakianalytics.orianna.types.data.staticdata.Languages();
        data.setPlatform(platform.getTag());
        return new Languages(data);
    }

    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.LanguageStrings data = new com.merakianalytics.orianna.types.data.staticdata.LanguageStrings();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        return new LanguageStrings(data);
    }

    @Get(League.class)
    public League getLeague(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Tier tier = (Tier)query.get("tier");
        final Queue queue = (Queue)query.get("queue");
        final String leagueId = (String)query.get("leagueId");

        if(leagueId == null) {
            if(tier == null || queue == null) {
                throw new QueryValidationException("Query was missing required parameters! Either leagueId or tier and queue must be included!");
            } else if(!UNIQUE_TIERS.contains(tier)) {
                final StringBuilder sb = new StringBuilder();
                for(final Tier t : UNIQUE_TIERS) {
                    sb.append(", " + t);
                }
                throw new QueryValidationException("Query contained invalid parameters! tier must be one of [" + sb.substring(2) + "]!");
            } else if(!Queue.RANKED.contains(queue)) {
                final StringBuilder sb = new StringBuilder();
                for(final Queue q : Queue.RANKED) {
                    sb.append(", " + q);
                }
                throw new QueryValidationException("Query contained invalid parameters! queue must be one of [" + sb.substring(2) + "]!");
            }
        }

        final com.merakianalytics.orianna.types.data.league.League league = new com.merakianalytics.orianna.types.data.league.League();
        league.setPlatform(platform.getTag());
        if(leagueId != null) {
            league.setId(leagueId);
        } else {
            league.setTier(tier.name());
            league.setQueue(queue.name());
        }
        return new League(league);
    }

    @Get(LeaguePositions.class)
    public LeaguePositions getLeaguePositions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number summonerId = (Number)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final com.merakianalytics.orianna.types.data.league.LeaguePositions data =
            new com.merakianalytics.orianna.types.data.league.LeaguePositions();
        data.setPlatform(platform.getTag());
        data.setSummonerId(summonerId.longValue());
        return new LeaguePositions(data);
    }

    @SuppressWarnings("unchecked")
    @GetMany(Champion.class)
    public CloseableIterator<Champion> getManyChampion(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        final Iterable<String> keys = (Iterable<String>)query.get("keys");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names", keys, "keys");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else if(keys != null) {
            iterator = keys.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Champion>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Champion next() {
                final com.merakianalytics.orianna.types.data.staticdata.Champion data = new com.merakianalytics.orianna.types.data.staticdata.Champion();
                if(ids != null) {
                    final int id = ((Number)iterator.next()).intValue();
                    data.setId(id);
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else if(keys != null) {
                    data.setKey((String)iterator.next());
                }
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                data.setIncludedData(includedData);
                return new Champion(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionMasteries.class)
    public CloseableIterator<ChampionMasteries> getManyChampionMasteries(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<String> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<ChampionMasteries>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMasteries next() {
                final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries data =
                    new com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries();
                data.setPlatform(platform.getTag());
                data.setSummonerId(iterator.next());
                return new ChampionMasteries(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionMastery.class)
    public CloseableIterator<ChampionMastery> getManyChampionMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        final Iterable<Number> championIds = (Iterable<Number>)query.get("championIds");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId", championIds, "championIds");

        final Iterator<Number> iterator = championIds.iterator();
        return CloseableIterators.from(new Iterator<ChampionMastery>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMastery next() {
                final com.merakianalytics.orianna.types.data.championmastery.ChampionMastery data =
                    new com.merakianalytics.orianna.types.data.championmastery.ChampionMastery();
                data.setPlatform(platform.getTag());
                data.setSummonerId(summonerId);
                data.setChampionId(iterator.next().intValue());
                return new ChampionMastery(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionMasteryScore.class)
    public CloseableIterator<ChampionMasteryScore> getManyChampionMasteryScore(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<String> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<ChampionMasteryScore>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMasteryScore next() {
                final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore data =
                    new com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore();
                data.setPlatform(platform.getTag());
                data.setSummonerId(iterator.next());
                return new ChampionMasteryScore(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionRotation.class)
    public CloseableIterator<ChampionRotation> getManyChampionRotation(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<ChampionRotation>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionRotation next() {
                final com.merakianalytics.orianna.types.data.champion.ChampionRotation data =
                    new com.merakianalytics.orianna.types.data.champion.ChampionRotation();
                data.setPlatform(iterator.next().getTag());
                return new ChampionRotation(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(CurrentMatch.class)
    public CloseableIterator<CurrentMatch> getManyCurrentMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<CurrentMatch>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public CurrentMatch next() {
                final com.merakianalytics.orianna.types.data.spectator.CurrentMatch data = new com.merakianalytics.orianna.types.data.spectator.CurrentMatch();
                data.setPlatform(platform.getTag());
                data.setSummonerId(iterator.next().longValue());
                return new CurrentMatch(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(FeaturedMatches.class)
    public CloseableIterator<FeaturedMatches> getManyFeaturedMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<FeaturedMatches>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public FeaturedMatches next() {
                final com.merakianalytics.orianna.types.data.spectator.FeaturedMatches data =
                    new com.merakianalytics.orianna.types.data.spectator.FeaturedMatches();
                data.setPlatform(iterator.next().getTag());
                return new FeaturedMatches(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Item.class)
    public CloseableIterator<Item> getManyItem(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Item next() {
                final com.merakianalytics.orianna.types.data.staticdata.Item data = new com.merakianalytics.orianna.types.data.staticdata.Item();
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                data.setIncludedData(includedData);
                if(ids != null) {
                    data.setId(((Number)iterator.next()).intValue());
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else {
                    return null;
                }
                return new Item(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(League.class)
    public CloseableIterator<League> getManyLeague(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<String> ids = (Iterable<String>)query.get("leagueIds");
        final Tier tier = (Tier)query.get("tier");
        final Iterable<Queue> queues = (Iterable<Queue>)query.get("queues");

        if(ids == null) {
            if(tier == null || queues == null) {
                throw new QueryValidationException("Query was missing required parameters! Either leagueIds or tier and queues must be included!");
            } else if(!UNIQUE_TIERS.contains(tier)) {
                final StringBuilder sb = new StringBuilder();
                for(final Tier t : UNIQUE_TIERS) {
                    sb.append(", " + t);
                }
                throw new QueryValidationException("Query contained invalid parameters! tier must be one of [" + sb.substring(2) + "]!");
            }
        }

        final Iterator<?> iterator = ids != null ? ids.iterator() : queues.iterator();
        return CloseableIterators.from(new Iterator<League>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public League next() {
                final com.merakianalytics.orianna.types.data.league.League data =
                    new com.merakianalytics.orianna.types.data.league.League();
                data.setPlatform(platform.getTag());
                if(ids != null) {
                    data.setId((String)iterator.next());
                } else {
                    data.setTier(tier.name());
                    data.setQueue(((Queue)iterator.next()).name());
                }
                return new League(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(LeaguePositions.class)
    public CloseableIterator<LeaguePositions> getManyLeaguePositions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<LeaguePositions>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public LeaguePositions next() {
                final com.merakianalytics.orianna.types.data.league.LeaguePositions data =
                    new com.merakianalytics.orianna.types.data.league.LeaguePositions();
                data.setPlatform(platform.getTag());
                data.setSummonerId(iterator.next().longValue());
                return new LeaguePositions(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Map.class)
    public CloseableIterator<Map> getManyMap(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Map>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Map next() {
                final com.merakianalytics.orianna.types.data.staticdata.Map data = new com.merakianalytics.orianna.types.data.staticdata.Map();
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                if(ids != null) {
                    data.setId(((Number)iterator.next()).intValue());
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else {
                    return null;
                }
                return new Map(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Mastery.class)
    public CloseableIterator<Mastery> getManyMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Mastery>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Mastery next() {
                final com.merakianalytics.orianna.types.data.staticdata.Mastery data = new com.merakianalytics.orianna.types.data.staticdata.Mastery();
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                data.setIncludedData(includedData);
                if(ids != null) {
                    data.setId(((Number)iterator.next()).intValue());
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else {
                    return null;
                }
                return new Mastery(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Match.class)
    public CloseableIterator<Match> getManyMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> matchIds = (Iterable<Number>)query.get("matchIds");
        Utilities.checkNotNull(platform, "platform", matchIds, "matchIds");

        final Iterator<Number> iterator = matchIds.iterator();
        return CloseableIterators.from(new Iterator<Match>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Match next() {
                final com.merakianalytics.orianna.types.data.match.Match data = new com.merakianalytics.orianna.types.data.match.Match();
                data.setPlatform(platform.getTag());
                data.setId(iterator.next().longValue());
                return new Match(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(MatchHistory.class)
    public CloseableIterator<MatchHistory> getManyMatchHistory(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> accountIds = (Iterable<Number>)query.get("accountIds");
        final Set<Integer> queues = query.get("queues") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("queues");
        final Set<Integer> seasons = query.get("seasons") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("seasons");
        final Set<Integer> champions = query.get("champions") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("champions");
        final Number beginTime = (Number)query.get("beginTime");
        final Number endTime = (Number)query.get("endTime");
        final Number beginIndex = (Number)query.get("beginIndex");
        final Number endIndex = (Number)query.get("endIndex");
        final boolean recent = query.get("recent") == null ? false : (Boolean)query.get("recent");
        Utilities.checkNotNull(platform, "platform", accountIds, "accountIds");

        final Iterator<Number> iterator = accountIds.iterator();
        return CloseableIterators.from(new Iterator<MatchHistory>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MatchHistory next() {
                final com.merakianalytics.orianna.types.data.match.MatchList data =
                    new com.merakianalytics.orianna.types.data.match.MatchList();
                data.setPlatform(platform.getTag());
                data.setAccountId(iterator.next().longValue());
                data.setQueues(queues);
                data.setSeasons(seasons);
                data.setChampions(champions);
                data.setStartTime(beginTime == null ? null : new DateTime(beginTime.longValue()));
                data.setEndTime(endTime == null ? null : new DateTime(endTime.longValue()));
                data.setStartIndex(beginIndex == null ? 0 : beginIndex.intValue());
                data.setEndIndex(endIndex == null ? 0 : endIndex.intValue());
                data.setRecent(recent);
                return new MatchHistory(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Patch.class)
    public CloseableIterator<Patch> getManyPatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkNotNull(platform, "platform", names, "names");

        final Iterator<String> iterator = names.iterator();
        return CloseableIterators.from(new Iterator<Patch>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Patch next() {
                final com.merakianalytics.orianna.types.data.staticdata.Patch data = new com.merakianalytics.orianna.types.data.staticdata.Patch();
                data.setPlatform(platform.getTag());
                data.setName(iterator.next());
                return new Patch(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ProfileIcon.class)
    public CloseableIterator<ProfileIcon> getManyProfileIcon(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<ProfileIcon>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProfileIcon next() {
                final com.merakianalytics.orianna.types.data.staticdata.ProfileIcon data = new com.merakianalytics.orianna.types.data.staticdata.ProfileIcon();
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                data.setId(iterator.next().intValue());
                return new ProfileIcon(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Realm.class)
    public CloseableIterator<Realm> getManyRealm(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Realm>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Realm next() {
                final com.merakianalytics.orianna.types.data.staticdata.Realm data =
                    new com.merakianalytics.orianna.types.data.staticdata.Realm();
                data.setPlatform(iterator.next().getTag());
                return new Realm(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ReforgedRune.class)
    public CloseableIterator<ReforgedRune> getManyReforgedRune(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        final Iterable<String> keys = (Iterable<String>)query.get("keys");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names", keys, "keys");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else if(keys != null) {
            iterator = keys.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<ReforgedRune>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ReforgedRune next() {
                final com.merakianalytics.orianna.types.data.staticdata.ReforgedRune data =
                    new com.merakianalytics.orianna.types.data.staticdata.ReforgedRune();
                if(ids != null) {
                    final int id = ((Number)iterator.next()).intValue();
                    data.setId(id);
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else if(keys != null) {
                    data.setKey((String)iterator.next());
                }
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                return new ReforgedRune(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Rune.class)
    public CloseableIterator<Rune> getManyRune(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Rune>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Rune next() {
                final com.merakianalytics.orianna.types.data.staticdata.Rune data = new com.merakianalytics.orianna.types.data.staticdata.Rune();
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                data.setIncludedData(includedData);
                if(ids != null) {
                    data.setId(((Number)iterator.next()).intValue());
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else {
                    return null;
                }
                return new Rune(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ShardStatus.class)
    public CloseableIterator<ShardStatus> getManyShardStatus(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<ShardStatus>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ShardStatus next() {
                final com.merakianalytics.orianna.types.data.status.ShardStatus data =
                    new com.merakianalytics.orianna.types.data.status.ShardStatus();
                data.setPlatform(iterator.next().getTag());
                return new ShardStatus(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Summoner.class)
    public CloseableIterator<Summoner> getManySummoner(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<String> puuids = (Iterable<String>)query.get("puuids");
        final Iterable<String> accountIds = (Iterable<String>)query.get("accountIds");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("ids");
        final Iterable<String> summonerNames = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(puuids, "puuids", accountIds, "accountIds", summonerIds, "ids", summonerNames, "names");

        final Iterator<String> iterator = puuids != null ? puuids.iterator()
            : accountIds != null ? accountIds.iterator() : summonerIds != null ? summonerIds.iterator() : summonerNames.iterator();
        return CloseableIterators.from(new Iterator<Summoner>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Summoner next() {
                final com.merakianalytics.orianna.types.data.summoner.Summoner data = new com.merakianalytics.orianna.types.data.summoner.Summoner();
                data.setPlatform(platform.getTag());
                if(puuids != null) {
                    data.setPuuid(iterator.next());
                } else if(accountIds != null) {
                    data.setAccountId(iterator.next());
                } else if(summonerIds != null) {
                    data.setId(iterator.next());
                } else if(summonerNames != null) {
                    data.setName(iterator.next());
                } else {
                    return null;
                }
                return new Summoner(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(SummonerSpell.class)
    public CloseableIterator<SummonerSpell> getManySummonerSpell(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<SummonerSpell>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public SummonerSpell next() {
                final com.merakianalytics.orianna.types.data.staticdata.SummonerSpell data =
                    new com.merakianalytics.orianna.types.data.staticdata.SummonerSpell();
                data.setPlatform(platform.getTag());
                data.setVersion(version);
                data.setLocale(locale);
                data.setIncludedData(includedData);
                if(ids != null) {
                    data.setId(((Number)iterator.next()).intValue());
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else {
                    return null;
                }
                return new SummonerSpell(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Timeline.class)
    public CloseableIterator<Timeline> getManyTimeline(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> matchIds = (Iterable<Number>)query.get("matchIds");
        Utilities.checkNotNull(platform, "platform", matchIds, "matchIds");

        final Iterator<Number> iterator = matchIds.iterator();
        return CloseableIterators.from(new Iterator<Timeline>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Timeline next() {
                final com.merakianalytics.orianna.types.data.match.Timeline data = new com.merakianalytics.orianna.types.data.match.Timeline();
                data.setPlatform(platform.getTag());
                data.setId(iterator.next().longValue());
                return new Timeline(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(TournamentMatches.class)
    public CloseableIterator<TournamentMatches> getManyTournamentMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> tournamentCodes = (Iterable<String>)query.get("tournamentCodes");
        Utilities.checkNotNull(platform, "platform", tournamentCodes, "tournamentCodes");

        final Iterator<String> iterator = tournamentCodes.iterator();
        return CloseableIterators.from(new Iterator<TournamentMatches>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public TournamentMatches next() {
                final com.merakianalytics.orianna.types.data.match.TournamentMatches data =
                    new com.merakianalytics.orianna.types.data.match.TournamentMatches();
                data.setPlatform(platform.getTag());
                data.setTournamentCode(iterator.next());
                return new TournamentMatches(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(VerificationString.class)
    public CloseableIterator<VerificationString> getManyVerificationString(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<VerificationString>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public VerificationString next() {
                final com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString data =
                    new com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString();
                data.setPlatform(platform.getTag());
                data.setSummonerId(iterator.next().longValue());
                return new VerificationString(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(Map.class)
    public Map getMap(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.Map data = new com.merakianalytics.orianna.types.data.staticdata.Map();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        return new Map(data);
    }

    @Get(Maps.class)
    public Maps getMaps(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.Maps data = new com.merakianalytics.orianna.types.data.staticdata.Maps();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        return new Maps(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Masteries.class)
    public Masteries getMasteries(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Masteries data = new com.merakianalytics.orianna.types.data.staticdata.Masteries();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Masteries(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Mastery.class)
    public Mastery getMastery(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Mastery data = new com.merakianalytics.orianna.types.data.staticdata.Mastery();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Mastery(data);
    }

    @Get(Match.class)
    public Match getMatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number matchId = (Number)query.get("matchId");
        Utilities.checkNotNull(platform, "platform", matchId, "matchId");
        final String tournamentCode = (String)query.get("tournamentCode");

        final com.merakianalytics.orianna.types.data.match.Match data =
            new com.merakianalytics.orianna.types.data.match.Match();
        data.setPlatform(platform.getTag());
        data.setId(matchId.longValue());
        data.setTournamentCode(tournamentCode);
        return new Match(data);
    }

    @SuppressWarnings("unchecked")
    @Get(MatchHistory.class)
    public MatchHistory getMatchHistory(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number accountId = (Number)query.get("accountId");
        final Set<Integer> queues = query.get("queues") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("queues");
        final Set<Integer> seasons = query.get("seasons") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("seasons");
        final Set<Integer> champions = query.get("champions") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("champions");
        final Number beginTime = (Number)query.get("beginTime");
        final Number endTime = (Number)query.get("endTime");
        final Number beginIndex = (Number)query.get("beginIndex");
        final Number endIndex = (Number)query.get("endIndex");
        final boolean recent = query.get("recent") == null ? false : (Boolean)query.get("recent");
        Utilities.checkNotNull(platform, "platform", accountId, "accountId");

        final com.merakianalytics.orianna.types.data.match.MatchList data =
            new com.merakianalytics.orianna.types.data.match.MatchList();
        data.setPlatform(platform.getTag());
        data.setAccountId(accountId.longValue());
        data.setQueues(queues);
        data.setSeasons(seasons);
        data.setChampions(champions);
        data.setStartTime(beginTime == null ? null : new DateTime(beginTime.longValue()));
        data.setEndTime(endTime == null ? null : new DateTime(endTime.longValue()));
        data.setStartIndex(beginIndex == null ? 0 : beginIndex.intValue());
        data.setEndIndex(endIndex == null ? 0 : endIndex.intValue());
        data.setRecent(recent);
        return new MatchHistory(data);
    }

    @Get(Patch.class)
    public Patch getPatch(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String name = (String)query.get("name");

        final com.merakianalytics.orianna.types.data.staticdata.Patch data = new com.merakianalytics.orianna.types.data.staticdata.Patch();
        data.setName(name);
        data.setPlatform(platform.getTag());
        return new Patch(data);
    }

    @Get(Patches.class)
    public Patches getPatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.staticdata.Patches data = new com.merakianalytics.orianna.types.data.staticdata.Patches();
        data.setPlatform(platform.getTag());
        return new Patches(data);
    }

    @Get(ProfileIcon.class)
    public ProfileIcon getProfileIcon(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number id = (Number)query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.ProfileIcon data = new com.merakianalytics.orianna.types.data.staticdata.ProfileIcon();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setId(id.intValue());
        return new ProfileIcon(data);
    }

    @Get(ProfileIcons.class)
    public ProfileIcons getProfileIcons(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.ProfileIcons data = new com.merakianalytics.orianna.types.data.staticdata.ProfileIcons();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        return new ProfileIcons(data);
    }

    @Get(Realm.class)
    public Realm getRealm(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.staticdata.Realm data = new com.merakianalytics.orianna.types.data.staticdata.Realm();
        data.setPlatform(platform.getTag());
        return new Realm(data);
    }

    @Get(ReforgedRune.class)
    public ReforgedRune getReforgedRune(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        final String key = (String)query.get("key");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name", key, "key");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.ReforgedRune data = new com.merakianalytics.orianna.types.data.staticdata.ReforgedRune();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setKey(key);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        return new ReforgedRune(data);
    }

    @Get(ReforgedRunes.class)
    public ReforgedRunes getReforgedRunes(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.ReforgedRunes data = new com.merakianalytics.orianna.types.data.staticdata.ReforgedRunes();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        return new ReforgedRunes(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Rune.class)
    public Rune getRune(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Rune data = new com.merakianalytics.orianna.types.data.staticdata.Rune();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Rune(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Runes.class)
    public Runes getRunes(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Runes data = new com.merakianalytics.orianna.types.data.staticdata.Runes();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Runes(data);
    }

    @Get(ShardStatus.class)
    public ShardStatus getShardStatus(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.status.ShardStatus data = new com.merakianalytics.orianna.types.data.status.ShardStatus();
        data.setPlatform(platform.getTag());
        return new ShardStatus(data);
    }

    @Get(Summoner.class)
    public Summoner getSummoner(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String puuid = (String)query.get("puuid");
        final String accountId = (String)query.get("accountId");
        final String id = (String)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(puuid, "puuid", accountId, "accountId", id, "id", name, "name");

        final com.merakianalytics.orianna.types.data.summoner.Summoner data = new com.merakianalytics.orianna.types.data.summoner.Summoner();
        data.setPlatform(platform.getTag());
        data.setPuuid(puuid);
        data.setAccountId(accountId);
        data.setId(id);
        data.setName(name);
        return new Summoner(data);
    }

    @SuppressWarnings("unchecked")
    @Get(SummonerSpell.class)
    public SummonerSpell getSummonerSpell(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.SummonerSpell data = new com.merakianalytics.orianna.types.data.staticdata.SummonerSpell();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new SummonerSpell(data);
    }

    @SuppressWarnings("unchecked")
    @Get(SummonerSpells.class)
    public SummonerSpells getSummonerSpells(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.SummonerSpells data = new com.merakianalytics.orianna.types.data.staticdata.SummonerSpells();
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new SummonerSpells(data);
    }

    @Get(Timeline.class)
    public Timeline getTimeline(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number matchId = (Number)query.get("matchId");
        Utilities.checkNotNull(platform, "platform", matchId, "matchId");

        final com.merakianalytics.orianna.types.data.match.Timeline data =
            new com.merakianalytics.orianna.types.data.match.Timeline();
        data.setPlatform(platform.getTag());
        data.setId(matchId.longValue());
        return new Timeline(data);
    }

    @Get(TournamentMatches.class)
    public TournamentMatches getTournamentMatches(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String tournamentCode = (String)query.get("tournamentCode");
        Utilities.checkNotNull(platform, "platform", tournamentCode, "tournamentCode");

        final com.merakianalytics.orianna.types.data.match.TournamentMatches data =
            new com.merakianalytics.orianna.types.data.match.TournamentMatches();
        data.setPlatform(platform.getTag());
        data.setTournamentCode(tournamentCode);
        return new TournamentMatches(data);
    }

    @Get(VerificationString.class)
    public VerificationString getVerificationString(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number summonerId = (Number)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString data =
            new com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString();
        data.setPlatform(platform.getTag());
        data.setSummonerId(summonerId.longValue());
        return new VerificationString(data);
    }

    @Get(Versions.class)
    public Versions getVersions(final java.util.Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.staticdata.Versions data = new com.merakianalytics.orianna.types.data.staticdata.Versions();
        data.setPlatform(platform.getTag());
        return new Versions(data);
    }
}
