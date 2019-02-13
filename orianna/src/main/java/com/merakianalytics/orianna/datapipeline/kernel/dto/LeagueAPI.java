package com.merakianalytics.orianna.datapipeline.kernel.dto;

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
import com.merakianalytics.orianna.datapipeline.kernel.dto.Kernel.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.dto.league.LeagueList;
import com.merakianalytics.orianna.types.dto.league.PositionalQueuesList;
import com.merakianalytics.orianna.types.dto.league.SummonerPositions;

public class LeagueAPI extends KernelService {
    // TODO: Add /lol/league/v4/positions/{positionalQueue}/{tier}/{division}/{position}/{page}

    private static final Map<Tier, String> LEAGUE_LIST_ENDPOINTS = ImmutableMap.of(Tier.CHALLENGER, "lol/league/v4/challengerleagues/by-queue/",
        Tier.MASTER, "lol/league/v4/masterleagues/by-queue/", Tier.GRANDMASTER, "lol/league/v4/grandmasterleagues/by-queue/");

    public LeagueAPI(final Configuration config, final HTTPClient client) {
        super(config, client);
    }

    @Get(LeagueList.class)
    public LeagueList getLeagueList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Tier tier = (Tier)query.get("tier");
        final Queue queue = (Queue)query.get("queue");
        final String leagueId = (String)query.get("leagueId");

        if(leagueId == null) {
            if(tier == null || queue == null) {
                throw new QueryValidationException("Query was missing required parameters! Either leagueId or tier and queue must be included!");
            } else if(!LEAGUE_LIST_ENDPOINTS.containsKey(tier)) {
                final StringBuilder sb = new StringBuilder();
                for(final Tier t : LEAGUE_LIST_ENDPOINTS.keySet()) {
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

        LeagueList data;
        if(leagueId == null) {
            final String endpoint = LEAGUE_LIST_ENDPOINTS.get(tier) + queue;
            data = get(LeagueList.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        } else {
            final String endpoint = "lol/league/v4/leagues/" + leagueId;
            data = get(LeagueList.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        }
        if(data == null) {
            return null;
        }

        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(LeagueList.class)
    public CloseableIterator<LeagueList> getManyLeagueList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Tier tier = (Tier)query.get("tier");
        final Iterable<Queue> queues = (Iterable<Queue>)query.get("queues");
        final Iterable<String> leagueIds = (Iterable<String>)query.get("leagueIds");

        if(leagueIds == null) {
            if(tier == null || queues == null) {
                throw new QueryValidationException("Query was missing required parameters! Either leagueIds or tier and queues must be included!");
            } else if(!LEAGUE_LIST_ENDPOINTS.containsKey(tier)) {
                final StringBuilder sb = new StringBuilder();
                for(final Tier t : LEAGUE_LIST_ENDPOINTS.keySet()) {
                    sb.append(", " + t);
                }
                throw new QueryValidationException("Query contained invalid parameters! tier must be one of [" + sb.substring(2) + "]!");
            }
        }

        final Iterator<?> iterator = leagueIds == null ? queues.iterator() : leagueIds.iterator();
        return CloseableIterators.from(new Iterator<LeagueList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public LeagueList next() {
                LeagueList data;
                if(leagueIds == null) {
                    final Queue queue = (Queue)iterator.next();

                    if(!Queue.RANKED.contains(queue)) {
                        return null;
                    }

                    final String endpoint = LEAGUE_LIST_ENDPOINTS.get(tier) + queue;
                    data = get(LeagueList.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                } else {
                    final String leagueId = (String)iterator.next();
                    final String endpoint = "lol/league/v4/leagues/" + leagueId;
                    data = get(LeagueList.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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
    @GetMany(PositionalQueuesList.class)
    public CloseableIterator<PositionalQueuesList> getManyPositionalQueuesList(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<PositionalQueuesList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public PositionalQueuesList next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/league/v4/positional-rank-queues";
                final PositionalQueuesList data = get(PositionalQueuesList.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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
    @GetMany(SummonerPositions.class)
    public CloseableIterator<SummonerPositions> getManySummonerPositions(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<String> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<SummonerPositions>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public SummonerPositions next() {
                final String summonerId = iterator.next();

                final String endpoint = "lol/league/v4/positions/by-summoner/" + summonerId;
                final SummonerPositions data = get(SummonerPositions.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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

    @Get(PositionalQueuesList.class)
    public PositionalQueuesList getPositionalQueuesList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/league/v4/positional-rank-queues";
        final PositionalQueuesList data = get(PositionalQueuesList.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }

    @Get(SummonerPositions.class)
    public SummonerPositions getSummonerPositions(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final String endpoint = "lol/league/v4/positions/by-summoner/" + summonerId;
        final SummonerPositions data = get(SummonerPositions.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }
}
