package com.merakianalytics.orianna.datapipeline.riotapi;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.RateLimiter;
import com.merakianalytics.orianna.type.common.Platform;
import com.merakianalytics.orianna.type.dto.match.Match;
import com.merakianalytics.orianna.type.dto.match.MatchTimeline;
import com.merakianalytics.orianna.type.dto.match.Matchlist;
import com.merakianalytics.orianna.type.dto.match.TournamentMatches;

public class MatchAPI extends RiotAPI.Service {
    public MatchAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client) {
        super(key, applicationRateLimiters, client);
    }

    @SuppressWarnings("unchecked")
    @GetMany(Match.class)
    public CloseableIterator<Match> getManyMatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Long> matchIds = (Iterable<Long>)query.get("matchIds");
        final long forAccountId = ((Number)query.get("forAccountId")).longValue();
        final String tournamentCode = (String)query.get("tournamentCode");

        final Iterator<Long> iterator = matchIds.iterator();
        return CloseableIterators.from(new Iterator<Match>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Match next() {
                final Long matchId = iterator.next();

                String endpoint;
                Match data;
                if(tournamentCode == null) {
                    endpoint = "lol/match/v3/matches/" + matchId;
                    data = get(Match.class, endpoint, platform, "lol/match/v3/matches/matchId");
                } else {
                    endpoint = "lol/match/v3/matches/" + matchId + "/by-tournament-code/" + tournamentCode;
                    data = get(Match.class, endpoint, platform, "lol/match/v3/matches/matchId/by-tournament-code/tournamentCode");
                }

                data.setForAccountId(forAccountId);
                return data;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Matchlist.class)
    public CloseableIterator<Matchlist> getManyMatchlist(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> accountIds = (Iterable<Number>)query.get("accountIds");
        final Set<Integer> queues = (Set<Integer>)query.get("queues");
        final Set<Integer> seasons = (Set<Integer>)query.get("seasons");
        final Set<Integer> champions = (Set<Integer>)query.get("champions");
        final Long startTime = ((Number)query.get("startTime")).longValue();
        final Long endTime = ((Number)query.get("endTime")).longValue();
        final Integer startIndex = ((Number)query.get("startIndex")).intValue();
        final Integer endIndex = ((Number)query.get("endIndex")).intValue();
        final boolean recent = (Boolean)query.get("recent");

        final Multimap<String, String> parameters = HashMultimap.create();
        if(!recent) {
            parameters.put("beginTime", startTime.toString());
            parameters.put("endTime", endTime.toString());
            parameters.put("beginIndex", startIndex.toString());
            parameters.put("endIndex", endIndex.toString());
            for(final Integer queue : queues) {
                parameters.put("queue", queue.toString());
            }
            for(final Integer season : seasons) {
                parameters.put("season", season.toString());
            }
            for(final Integer champion : champions) {
                parameters.put("champion", champion.toString());
            }
        }

        final Iterator<Number> iterator = accountIds.iterator();
        return CloseableIterators.from(new Iterator<Matchlist>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Matchlist next() {
                final long accountId = iterator.next().longValue();

                String endpoint;
                Matchlist data;
                if(recent) {
                    endpoint = "lol/match/v3/matchlists/by-account/" + accountId + "/recent";
                    data = get(Matchlist.class, endpoint, platform, "lol/match/v3/matchlists/by-account/accountId/recent");
                    data.setRecent(true);
                } else {
                    endpoint = "lol/match/v3/matchlists/by-account/" + accountId;

                    data = get(Matchlist.class, endpoint, platform, parameters, "lol/match/v3/matchlists/by-account/{accountId}");
                    data.setRecent(false);
                    data.setQueues(queues);
                    data.setSeasons(seasons);
                    data.setChampions(champions);
                    data.setStartIndex(startIndex);
                    data.setEndIndex(endIndex);
                    data.setStartTime(startTime);
                    data.setEndTime(endTime);
                }

                data.setPlatform(platform.getTag());
                data.setAccountId(accountId);
                return data;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(MatchTimeline.class)
    public CloseableIterator<MatchTimeline> getManyMatchTimeline(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> matchIds = (Iterable<Number>)query.get("matchIds");

        final Iterator<Number> iterator = matchIds.iterator();
        return CloseableIterators.from(new Iterator<MatchTimeline>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MatchTimeline next() {
                final long matchId = iterator.next().longValue();

                final String endpoint = "lol/match/v3/timelines/by-match/" + matchId;
                final MatchTimeline data = get(MatchTimeline.class, endpoint, platform, "lol/match/v3/timelines/by-match/matchId");

                data.setPlatform(platform.getTag());
                data.setMatchId(matchId);
                return data;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(TournamentMatches.class)
    public CloseableIterator<TournamentMatches> getManyTournamentMatches(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> tournamentCodes = (Iterable<String>)query.get("tournamentCodes");

        final Iterator<String> iterator = tournamentCodes.iterator();
        return CloseableIterators.from(new Iterator<TournamentMatches>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public TournamentMatches next() {
                final String tournamentCode = iterator.next();

                final String endpoint = "lol/match/v3/matches/by-tournament-code/" + tournamentCode + "/ids";
                final TournamentMatches data = get(TournamentMatches.class, endpoint, platform, "lol/match/v3/matches/by-tournament-code/tournamentCode/ids");

                data.setPlatform(platform.getTag());
                data.setTournamentCode(tournamentCode);
                return data;
            }
        });
    }

    @Get(Match.class)
    public Match getMatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final long matchId = ((Number)query.get("matchId")).longValue();
        final long forAccountId = ((Number)query.get("forAccountId")).longValue();
        final String tournamentCode = (String)query.get("tournamentCode");

        String endpoint;
        Match data;
        if(tournamentCode == null) {
            endpoint = "lol/match/v3/matches/" + matchId;
            data = get(Match.class, endpoint, platform, "lol/match/v3/matches/matchId");
        } else {
            endpoint = "lol/match/v3/matches/" + matchId + "/by-tournament-code/" + tournamentCode;
            data = get(Match.class, endpoint, platform, "lol/match/v3/matches/matchId/by-tournament-code/tournamentCode");
        }

        data.setForAccountId(forAccountId);
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(Matchlist.class)
    public Matchlist getMatchlist(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final long accountId = ((Number)query.get("accountId")).longValue();
        final Set<Integer> queues = (Set<Integer>)query.get("queues");
        final Set<Integer> seasons = (Set<Integer>)query.get("seasons");
        final Set<Integer> champions = (Set<Integer>)query.get("champions");
        final Long startTime = ((Number)query.get("startTime")).longValue();
        final Long endTime = ((Number)query.get("endTime")).longValue();
        final Integer startIndex = ((Number)query.get("startIndex")).intValue();
        final Integer endIndex = ((Number)query.get("endIndex")).intValue();
        final boolean recent = (Boolean)query.get("recent");

        String endpoint;
        Matchlist data;
        if(recent) {
            endpoint = "lol/match/v3/matchlists/by-account/" + accountId + "/recent";
            data = get(Matchlist.class, endpoint, platform, "lol/match/v3/matchlists/by-account/accountId/recent");
            data.setRecent(true);
        } else {
            endpoint = "lol/match/v3/matchlists/by-account/" + accountId;

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("beginTime", startTime.toString());
            parameters.put("endTime", endTime.toString());
            parameters.put("beginIndex", startIndex.toString());
            parameters.put("endIndex", endIndex.toString());
            for(final Integer queue : queues) {
                parameters.put("queue", queue.toString());
            }
            for(final Integer season : seasons) {
                parameters.put("season", season.toString());
            }
            for(final Integer champion : champions) {
                parameters.put("champion", champion.toString());
            }

            data = get(Matchlist.class, endpoint, platform, parameters, "lol/match/v3/matchlists/by-account/{accountId}");
            data.setRecent(false);
            data.setQueues(queues);
            data.setSeasons(seasons);
            data.setChampions(champions);
            data.setStartIndex(startIndex);
            data.setEndIndex(endIndex);
            data.setStartTime(startTime);
            data.setEndTime(endTime);
        }

        data.setPlatform(platform.getTag());
        data.setAccountId(accountId);
        return data;
    }

    @Get(MatchTimeline.class)
    public MatchTimeline getMatchTimeline(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final long matchId = ((Number)query.get("matchId")).longValue();

        final String endpoint = "lol/match/v3/timelines/by-match/" + matchId;
        final MatchTimeline data = get(MatchTimeline.class, endpoint, platform, "lol/match/v3/timelines/by-match/matchId");

        data.setPlatform(platform.getTag());
        data.setMatchId(matchId);
        return data;
    }

    @Get(TournamentMatches.class)
    public TournamentMatches getTournamentMatches(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String tournamentCode = (String)query.get("tournamentCode");

        final String endpoint = "lol/match/v3/matches/by-tournament-code/" + tournamentCode + "/ids";
        final TournamentMatches data = get(TournamentMatches.class, endpoint, platform, "lol/match/v3/matches/by-tournament-code/tournamentCode/ids");

        data.setPlatform(platform.getTag());
        data.setTournamentCode(tournamentCode);
        return data;
    }
}
