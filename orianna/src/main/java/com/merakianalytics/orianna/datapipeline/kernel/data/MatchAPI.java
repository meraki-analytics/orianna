package com.merakianalytics.orianna.datapipeline.kernel.data;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Weeks;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.kernel.data.Kernel.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.match.Match;
import com.merakianalytics.orianna.types.data.match.MatchList;
import com.merakianalytics.orianna.types.data.match.Timeline;
import com.merakianalytics.orianna.types.data.match.TournamentMatches;

public class MatchAPI extends KernelService {
    private static final Duration HISTORY_LENGTH = Days.days(730).toStandardDuration();  // 2 Years
    private static final int MAX_MATCH_INDEX_DIFFERENCE = 100;
    private static final long ONE_WEEK_IN_MILLISECONDS = Weeks.ONE.toStandardDuration().getMillis();

    public MatchAPI(final Configuration config, final HTTPClient client) {
        super(config, client);
    }

    @SuppressWarnings("unchecked")
    @GetMany(Match.class)
    public CloseableIterator<Match> getManyMatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> matchIds = (Iterable<Number>)query.get("matchIds");
        final String tournamentCode = (String)query.get("tournamentCode");
        Utilities.checkNotNull(platform, "platform", matchIds, "matchIds");

        final Iterator<Number> iterator = matchIds.iterator();
        return CloseableIterators.from(new Iterator<Match>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Match next() {
                final Number matchId = iterator.next();

                String endpoint;
                Match data;
                if(tournamentCode == null) {
                    endpoint = "lol/match/v4/matches/" + matchId;
                    data = get(Match.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                } else {
                    endpoint = "lol/match/v4/matches/" + matchId + "/by-tournament-code/" + tournamentCode;
                    data = get(Match.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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
    @GetMany(MatchList.class)
    public CloseableIterator<MatchList> getManyMatchList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> accountIds = (Iterable<String>)query.get("accountIds");
        final Set<Integer> queues = query.get("queues") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("queues");
        final Set<Integer> seasons = query.get("seasons") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("seasons");
        final Set<Integer> champions = query.get("champions") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("champions");
        Number beginTime = (Number)query.get("beginTime");
        Number endTime = (Number)query.get("endTime");
        Number beginIndex = (Number)query.get("beginIndex");
        Number endIndex = (Number)query.get("endIndex");
        Utilities.checkNotNull(platform, "platform", accountIds, "accountIds");

        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final DateTime historyStart = now.minusYears(3);

        // Time Handling
        if(beginTime != null && beginTime.longValue() < historyStart.getMillis()) {
            beginTime = historyStart.getMillis();
        }

        if(endTime != null && endTime.longValue() > now.getMillis()) {
            endTime = now.getMillis();
        }

        if(endTime != null && (beginTime == null || endTime.longValue() - beginTime.longValue() > ONE_WEEK_IN_MILLISECONDS)) {
            beginTime = Math.max(endTime.longValue() - ONE_WEEK_IN_MILLISECONDS, 0);
        }

        // Index Handling
        if(beginIndex != null && endIndex != null && beginIndex.intValue() + MAX_MATCH_INDEX_DIFFERENCE < endIndex.intValue()) {
            endIndex = beginIndex.intValue() + MAX_MATCH_INDEX_DIFFERENCE;
        }

        if(beginIndex != null && endIndex == null && endTime == null) {
            endIndex = beginIndex.intValue() + MAX_MATCH_INDEX_DIFFERENCE;
        }

        if(endIndex != null && beginIndex == null) {
            beginIndex = Math.max(endIndex.intValue() - MAX_MATCH_INDEX_DIFFERENCE, 0);
        }

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("platform", platform.getTag());
        if(beginTime != null) {
            parameters.put("beginTime", beginTime.toString());
        }
        if(endTime != null) {
            parameters.put("endTime", endTime.toString());
        }
        if(beginIndex != null) {
            parameters.put("beginIndex", beginIndex.toString());
        }
        if(endIndex != null) {
            parameters.put("endIndex", endIndex.toString());
        }
        for(final Integer queue : queues) {
            parameters.put("queue", queue.toString());
        }
        for(final Integer season : seasons) {
            parameters.put("season", season.toString());
        }
        for(final Integer champion : champions) {
            parameters.put("champion", champion.toString());
        }

        final Number bTime = beginTime;
        final Number eTime = endTime;
        final Number bIndex = beginIndex;
        final Number eIndex = endIndex;
        final Iterator<String> iterator = accountIds.iterator();
        return CloseableIterators.from(new Iterator<MatchList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MatchList next() {
                final String accountId = iterator.next();
                final String endpoint = "lol/match/v4/matchlists/by-account/" + accountId;
                final MatchList data = get(MatchList.class, endpoint, parameters);
                if(data == null) {
                    final MatchList empty = new MatchList();
                    empty.setPlatform(platform.getTag());
                    empty.setAccountId(accountId);
                    empty.setQueues(queues);
                    empty.setSeasons(seasons);
                    empty.setChampions(champions);
                    empty.setStartTime(new DateTime(bTime == null ? 0L : bTime.longValue()));
                    empty.setEndTime(new DateTime(eTime == null ? 0L : eTime.longValue()));
                    empty.setStartIndex(bIndex == null ? 0 : bIndex.intValue());
                    empty.setEndIndex(eIndex == null ? 0 : eIndex.intValue());
                    empty.setMaxSize(bTime != null && eTime != null ? Integer.MAX_VALUE : MAX_MATCH_INDEX_DIFFERENCE);
                    empty.setMaxTimeRange(Duration.millis(eTime != null ? Long.MAX_VALUE : ONE_WEEK_IN_MILLISECONDS));
                    empty.setHistoryLength(HISTORY_LENGTH);
                    return empty;
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
    @GetMany(Timeline.class)
    public CloseableIterator<Timeline> getManyTimeline(final Map<String, Object> query, final PipelineContext context) {
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
                final Number matchId = iterator.next();

                final String endpoint = "lol/match/v4/timelines/by-match/" + matchId;
                final Timeline data = get(Timeline.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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
    @GetMany(TournamentMatches.class)
    public CloseableIterator<TournamentMatches> getManyTournamentMatches(final Map<String, Object> query, final PipelineContext context) {
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
                final String tournamentCode = iterator.next();

                final String endpoint = "lol/match/v4/matches/by-tournament-code/" + tournamentCode + "/ids";
                final TournamentMatches data = get(TournamentMatches.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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

    @Get(Match.class)
    public Match getMatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number matchId = (Number)query.get("matchId");
        final String tournamentCode = (String)query.get("tournamentCode");
        Utilities.checkNotNull(platform, "platform", matchId, "matchId");

        String endpoint;
        Match data;
        if(tournamentCode == null) {
            endpoint = "lol/match/v4/matches/" + matchId;
            data = get(Match.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        } else {
            endpoint = "lol/match/v4/matches/" + matchId + "/by-tournament-code/" + tournamentCode;
            data = get(Match.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        }
        if(data == null) {
            return null;
        }

        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(MatchList.class)
    public MatchList getMatchList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String accountId = (String)query.get("accountId");
        final Set<Integer> queues = query.get("queues") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("queues");
        final Set<Integer> seasons = query.get("seasons") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("seasons");
        final Set<Integer> champions = query.get("champions") == null ? Collections.<Integer> emptySet() : (Set<Integer>)query.get("champions");
        Number beginTime = (Number)query.get("beginTime");
        Number endTime = (Number)query.get("endTime");
        Number beginIndex = (Number)query.get("beginIndex");
        Number endIndex = (Number)query.get("endIndex");
        Utilities.checkNotNull(platform, "platform", accountId, "accountId");

        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final DateTime historyStart = now.minusYears(3);

        // Time Handling
        if(beginTime != null && beginTime.longValue() < historyStart.getMillis()) {
            beginTime = historyStart.getMillis();
        }

        if(endTime != null && endTime.longValue() > now.getMillis()) {
            endTime = now.getMillis();
        }

        if(endTime != null && (beginTime == null || endTime.longValue() - beginTime.longValue() > ONE_WEEK_IN_MILLISECONDS)) {
            beginTime = Math.max(endTime.longValue() - ONE_WEEK_IN_MILLISECONDS, 0);
        }

        // Index Handling
        if(beginIndex != null && endIndex != null && beginIndex.intValue() + MAX_MATCH_INDEX_DIFFERENCE < endIndex.intValue()) {
            endIndex = beginIndex.intValue() + MAX_MATCH_INDEX_DIFFERENCE;
        }

        if(beginIndex != null && endIndex == null && endTime == null) {
            endIndex = beginIndex.intValue() + MAX_MATCH_INDEX_DIFFERENCE;
        }

        if(endIndex != null && beginIndex == null) {
            beginIndex = Math.max(endIndex.intValue() - MAX_MATCH_INDEX_DIFFERENCE, 0);
        }

        final String endpoint = "lol/match/v4/matchlists/by-account/" + accountId;

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("platform", platform.getTag());
        if(beginTime != null) {
            parameters.put("beginTime", beginTime.toString());
        }
        if(endTime != null) {
            parameters.put("endTime", endTime.toString());
        }
        if(beginIndex != null) {
            parameters.put("beginIndex", beginIndex.toString());
        }
        if(endIndex != null) {
            parameters.put("endIndex", endIndex.toString());
        }
        for(final Integer queue : queues) {
            parameters.put("queue", queue.toString());
        }
        for(final Integer season : seasons) {
            parameters.put("season", season.toString());
        }
        for(final Integer champion : champions) {
            parameters.put("champion", champion.toString());
        }

        final MatchList data = get(MatchList.class, endpoint, parameters);
        if(data == null) {
            final MatchList empty = new MatchList();
            empty.setPlatform(platform.getTag());
            empty.setAccountId(accountId);
            empty.setQueues(queues);
            empty.setSeasons(seasons);
            empty.setChampions(champions);
            empty.setStartTime(new DateTime(beginTime == null ? 0L : beginTime.longValue()));
            empty.setEndTime(new DateTime(endTime == null ? 0L : endTime.longValue()));
            empty.setStartIndex(beginIndex == null ? 0 : beginIndex.intValue());
            empty.setEndIndex(endIndex == null ? 0 : endIndex.intValue());
            empty.setMaxSize(beginTime != null && endTime != null ? Integer.MAX_VALUE : MAX_MATCH_INDEX_DIFFERENCE);
            empty.setMaxTimeRange(Duration.millis(endTime != null ? Long.MAX_VALUE : ONE_WEEK_IN_MILLISECONDS));
            empty.setHistoryLength(HISTORY_LENGTH);
            return empty;
        }

        return data;
    }

    @Get(Timeline.class)
    public Timeline getTimeline(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number matchId = (Number)query.get("matchId");
        Utilities.checkNotNull(platform, "platform", matchId, "matchId");

        final String endpoint = "lol/match/v4/timelines/by-match/" + matchId;
        final Timeline data = get(Timeline.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }

    @Get(TournamentMatches.class)
    public TournamentMatches getTournamentMatches(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String tournamentCode = (String)query.get("tournamentCode");
        Utilities.checkNotNull(platform, "platform", tournamentCode, "tournamentCode");

        final String endpoint = "lol/match/v4/matches/by-tournament-code/" + tournamentCode + "/ids";
        final TournamentMatches data = get(TournamentMatches.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }
}
