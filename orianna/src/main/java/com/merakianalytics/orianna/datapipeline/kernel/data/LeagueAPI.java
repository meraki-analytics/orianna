package com.merakianalytics.orianna.datapipeline.kernel.data;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.QueryValidationException;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.kernel.data.Kernel.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.data.league.League;
import com.merakianalytics.orianna.types.data.league.LeaguePositions;
import com.merakianalytics.orianna.types.data.league.PositionalQueues;

public class LeagueAPI extends KernelService {
    // TODO: Add /lol/league/v4/positions/{positionalQueue}/{tier}/{division}/{position}/{page}

    private static final Map<Tier, String> LEAGUE_ENDPOINTS = ImmutableMap.of(Tier.CHALLENGER, "lol/league/v4/challengerleagues/by-queue/",
        Tier.MASTER, "lol/league/v4/masterleagues/by-queue/", Tier.GRANDMASTER, "lol/league/v4/grandmasterleagues/by-queue/");

    public LeagueAPI(final Configuration config, final HTTPClient client) {
        super(config, client);
    }

    @Get(League.class)
    public League getLeague(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Tier tier = (Tier)query.get("tier");
        final Queue queue = (Queue)query.get("queue");
        final String leagueId = (String)query.get("leagueId");

        if(leagueId == null) {
            if(tier == null || queue == null) {
                throw new QueryValidationException("Query was missing required parameters! Either leagueId or tier and queue must be included!");
            } else if(!LEAGUE_ENDPOINTS.containsKey(tier)) {
                final StringBuilder sb = new StringBuilder();
                for(final Tier t : LEAGUE_ENDPOINTS.keySet()) {
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

        League data;
        if(leagueId == null) {
            final String endpoint = LEAGUE_ENDPOINTS.get(tier) + queue;
            data = get(League.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        } else {
            final String endpoint = "lol/league/v4/leagues/" + leagueId;
            data = get(League.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        }
        if(data == null) {
            return null;
        }

        return data;
    }

    @Get(LeaguePositions.class)
    public LeaguePositions getLeaguePositions(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final String endpoint = "lol/league/v4/positions/by-summoner/" + summonerId;
        final LeaguePositions data = get(LeaguePositions.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(League.class)
    public CloseableIterator<League> getManyLeague(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Tier tier = (Tier)query.get("tier");
        final Iterable<Queue> queues = (Iterable<Queue>)query.get("queues");
        final Iterable<String> leagueIds = (Iterable<String>)query.get("leagueIds");

        if(leagueIds == null) {
            if(tier == null || queues == null) {
                throw new QueryValidationException("Query was missing required parameters! Either leagueIds or tier and queues must be included!");
            } else if(!LEAGUE_ENDPOINTS.containsKey(tier)) {
                final StringBuilder sb = new StringBuilder();
                for(final Tier t : LEAGUE_ENDPOINTS.keySet()) {
                    sb.append(", " + t);
                }
                throw new QueryValidationException("Query contained invalid parameters! tier must be one of [" + sb.substring(2) + "]!");
            }
        }

        final Iterator<?> iterator = leagueIds == null ? queues.iterator() : leagueIds.iterator();
        return CloseableIterators.from(new Iterator<League>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public League next() {
                League data;
                if(leagueIds == null) {
                    final Queue queue = (Queue)iterator.next();

                    if(!Queue.RANKED.contains(queue)) {
                        return null;
                    }

                    final String endpoint = LEAGUE_ENDPOINTS.get(tier) + queue;
                    data = get(League.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                } else {
                    final String leagueId = (String)iterator.next();
                    final String endpoint = "lol/league/v4/leagues/" + leagueId;
                    data = get(League.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                }
                if(data == null) {
                    return null;
                }

                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(LeaguePositions.class)
    public CloseableIterator<LeaguePositions> getManyLeaguePositions(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<String> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<LeaguePositions>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public LeaguePositions next() {
                final String summonerId = iterator.next();

                final String endpoint = "lol/league/v4/positions/by-summoner/" + summonerId;
                final LeaguePositions data = get(LeaguePositions.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                if(data == null) {
                    return null;
                }

                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(PositionalQueues.class)
    public CloseableIterator<PositionalQueues> getManyPositionalQueues(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<PositionalQueues>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public PositionalQueues next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/league/v4/positional-rank-queues";
                final PositionalQueues data = get(PositionalQueues.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                if(data == null) {
                    return null;
                }

                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(PositionalQueues.class)
    public PositionalQueues getPositionalQueues(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/league/v4/positional-rank-queues";
        final PositionalQueues data = get(PositionalQueues.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }
}
