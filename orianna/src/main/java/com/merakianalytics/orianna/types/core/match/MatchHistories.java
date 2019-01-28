package com.merakianalytics.orianna.types.core.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Patch;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public abstract class MatchHistories {
    public static class Builder {
        public class RecentBuilder {
            public SearchableList<MatchHistory> get() {
                final List<String> ids = new ArrayList<>();
                final Iterator<Summoner> iterator = summoners.iterator();
                Summoner summoner = iterator.next();

                if(summoner == null) {
                    return SearchableLists.from(Collections.<MatchHistory> emptyList());
                }

                final Platform platform = summoner.getPlatform();
                ids.add(summoner.getAccountId());
                while(iterator.hasNext()) {
                    summoner = iterator.next();

                    if(platform != summoner.getPlatform()) {
                        throw new IllegalArgumentException("All summoners must be from the same platform/region!");
                    }
                    ids.add(summoner.getAccountId());
                }

                final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform)
                    .put("accountIds", ids).put("recent", Boolean.TRUE);

                final CloseableIterator<MatchHistory> result = Orianna.getSettings().getPipeline().getMany(MatchHistory.class, builder.build(), streaming);
                return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
            }

            public RecentBuilder streaming() {
                streaming = true;
                return this;
            }
        }

        private Set<Integer> champions;
        private Set<Integer> queues;
        private Set<Integer> seasons;
        private Integer startIndex, endIndex;
        private Long startTime, endTime;
        private boolean streaming = false;
        private final Iterable<Summoner> summoners;

        private Builder(final Iterable<Summoner> summoners) {
            this.summoners = summoners;
        }

        public RecentBuilder fromRecentMatches() {
            return new RecentBuilder();
        }

        public SearchableList<MatchHistory> get() {
            if(endTime != null && startTime == null) {
                throw new IllegalStateException("Can't set endTime without setting beginTime!");
            }

            final List<String> ids = new ArrayList<>();
            final Iterator<Summoner> iterator = summoners.iterator();
            Summoner summoner = iterator.next();

            if(summoner == null) {
                return SearchableLists.from(Collections.<MatchHistory> emptyList());
            }

            final Platform platform = summoner.getPlatform();
            ids.add(summoner.getAccountId());
            while(iterator.hasNext()) {
                summoner = iterator.next();

                if(platform != summoner.getPlatform()) {
                    throw new IllegalArgumentException("All summoners must be from the same platform/region!");
                }
                ids.add(summoner.getAccountId());
            }

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", platform).put("accountIds", ids);
            if(startIndex != null) {
                builder.put("beginIndex", startIndex);
            }
            if(endIndex != null) {
                builder.put("endIndex", endIndex);
            }
            if(startTime != null) {
                builder.put("beginTime", startTime);
            }
            if(endTime != null) {
                builder.put("endTime", endTime);
            }
            if(champions != null) {
                builder.put("champions", champions);
            }
            if(queues != null) {
                builder.put("queues", queues);
            }
            if(seasons != null) {
                builder.put("seasons", seasons);
            }

            final CloseableIterator<MatchHistory> result = Orianna.getSettings().getPipeline().getMany(MatchHistory.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public Builder onPatch(final Patch patch) {
            startTime = patch.getStartTime().getMillis();
            endTime = patch.getEndTime() == null ? System.currentTimeMillis() : patch.getEndTime().getMillis();
            return this;
        }

        public Builder streaming() {
            streaming = true;
            return this;
        }

        public Builder withChampions(final Champion... champions) {
            final HashSet<Integer> champs = new HashSet<>();
            for(final Champion champion : champions) {
                champs.add(champion.getId());
            }
            this.champions = champs;
            return this;
        }

        public Builder withChampions(final Iterable<Champion> champions) {
            final HashSet<Integer> champs = new HashSet<>();
            for(final Champion champion : champions) {
                champs.add(champion.getId());
            }
            this.champions = champs;
            return this;
        }

        public Builder withEndIndex(final int endIndex) {
            this.endIndex = endIndex;
            return this;
        }

        public Builder withEndTime(final DateTime endTime) {
            this.endTime = endTime.getMillis();
            return this;
        }

        public Builder withQueues(final Iterable<Queue> queues) {
            final HashSet<Integer> qs = new HashSet<>();
            for(final Queue queue : queues) {
                qs.add(queue.getId());
            }
            this.queues = qs;
            return this;
        }

        public Builder withQueues(final Queue... queues) {
            final HashSet<Integer> qs = new HashSet<>();
            for(final Queue queue : queues) {
                qs.add(queue.getId());
            }
            this.queues = qs;
            return this;
        }

        public Builder withSeasons(final Iterable<Season> seasons) {
            final HashSet<Integer> csons = new HashSet<>();
            for(final Season season : seasons) {
                csons.add(season.getId());
            }
            this.seasons = csons;
            return this;
        }

        public Builder withSeasons(final Season... seasons) {
            final HashSet<Integer> csons = new HashSet<>();
            for(final Season season : seasons) {
                csons.add(season.getId());
            }
            this.seasons = csons;
            return this;
        }

        public Builder withStartIndex(final int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        public Builder withStartTime(final DateTime startTime) {
            this.startTime = startTime.getMillis();
            return this;
        }
    }

    public static Builder forSummoners(final Iterable<Summoner> summoners) {
        return new Builder(summoners);
    }

    public static Builder forSummoners(final Summoner... summoners) {
        return new Builder(Arrays.asList(summoners));
    }
}
