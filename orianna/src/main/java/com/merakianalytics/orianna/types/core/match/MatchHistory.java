package com.merakianalytics.orianna.types.core.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.LazyList;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.data.match.MatchList;
import com.merakianalytics.orianna.types.data.match.MatchReference;

public class MatchHistory extends GhostObject<com.merakianalytics.orianna.types.data.match.MatchList> implements SearchableList<Match> {
    public static class Builder {
        public class RecentBuilder {
            public MatchHistory get() {
                final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform())
                    .put("accountId", summoner.getAccountId()).put("recent", Boolean.TRUE);

                return Orianna.getSettings().getPipeline().get(MatchHistory.class, builder.build());
            }
        }

        private Set<Integer> champions;
        private Set<Integer> queues;
        private Set<Integer> seasons;
        private Integer startIndex, endIndex;
        private Long startTime, endTime;
        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public RecentBuilder fromRecentMatches() {
            return new RecentBuilder();
        }

        public MatchHistory get() {
            if(endTime != null && startTime == null) {
                throw new IllegalStateException("Can't set endTime without setting beginTime!");
            }

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("accountId", summoner.getAccountId());
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

            return Orianna.getSettings().getPipeline().get(MatchHistory.class, builder.build());
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

    private class MatchIterator implements CloseableIterator<Match> {
        @Override
        public void close() {}

        @Override
        public boolean hasNext() {
            if(matchIterator.hasNext()) {
                return true;
            }

            final com.merakianalytics.orianna.types.data.match.MatchList list = loadBatch();
            if(list == null) {
                return false;
            }

            List<MatchReference> toAdd = new ArrayList<>(list.size());
            final int total = matchIterator.nextIndex() + list.size();
            if(coreData.getEndIndex() == 0 || total <= coreData.getEndIndex() - coreData.getStartIndex()) {
                toAdd = list;
            } else {
                final int needed = coreData.getEndIndex() - coreData.getStartIndex() - matchIterator.nextIndex();
                toAdd = list.subList(0, needed);
            }

            coreData.addAll(toAdd);
            matchIterator = coreData.listIterator(matchIterator.nextIndex());
            return matchIterator.hasNext();
        }

        @Override
        public Match next() {
            if(!hasNext()) {
                return null;
            }
            return new Match(matchIterator.next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static final String MATCH_HISTORY_LOAD_GROUP = "match-history";

    private static final long serialVersionUID = 2286295600096959941L;

    public static Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    private final Object batchLoadLock = new Object();

    private final Supplier<Set<Champion>> champions = Suppliers.memoize(new Supplier<Set<Champion>>() {
        @Override
        public Set<Champion> get() {
            return Collections
                .unmodifiableSet(new HashSet<>(Champions.withIds(coreData.getChampions()).withPlatform(Platform.withTag(coreData.getPlatform())).get()));
        }
    });

    private boolean complete = false;
    private DateTime endTime;
    private final SearchableList<Match> matches;
    private ListIterator<MatchReference> matchIterator = coreData.listIterator();
    private final Supplier<Set<Queue>> queues = Suppliers.memoize(new Supplier<Set<Queue>>() {
        @Override
        public Set<Queue> get() {
            final Set<Queue> queues = new HashSet<>();
            for(final Integer id : coreData.getQueues()) {
                queues.add(Queue.withId(id));
            }
            return Collections.unmodifiableSet(queues);
        }
    });
    private final Supplier<Set<Season>> seasons = Suppliers.memoize(new Supplier<Set<Season>>() {
        @Override
        public Set<Season> get() {
            final Set<Season> seasons = new HashSet<>();
            for(final Integer id : coreData.getSeasons()) {
                seasons.add(Season.withId(id));
            }
            return Collections.unmodifiableSet(seasons);
        }
    });

    private Integer startIndex;

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            return Summoner.withAccountId(coreData.getAccountId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public MatchHistory(final MatchList coreData) {
        super(coreData, 1);
        matches = SearchableLists.unmodifiableFrom(new LazyList<>(new MatchIterator()));
        startIndex = coreData.getStartIndex() == 0 && coreData.getEndIndex() == 0 ? null : coreData.getStartIndex();
        endTime = coreData.getEndTime();
    }

    @Override
    public void add(final int index, final Match element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(final Match e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final Collection<? extends Match> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends Match> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.containsAll(c);
    }

    @Override
    public void delete(final Object query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(final Predicate<Match> predicate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SearchableList<Match> filter(final Predicate<Match> predicate) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.filter(predicate);
    }

    @Override
    public SearchableList<Match> filter(final Predicate<Match> predicate, final boolean streaming) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.filter(predicate, streaming);
    }

    @Override
    public Match find(final Object query) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.find(query);
    }

    @Override
    public Match find(final Predicate<Match> predicate) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.find(predicate);
    }

    @Override
    public Match get(final int index) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.get(index);
    }

    public Set<Champion> getChampions() {
        return champions.get();
    }

    public int getEndIndex() {
        return coreData.getEndIndex();
    }

    public DateTime getEndTime() {
        return coreData.getEndTime();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            MATCH_HISTORY_LOAD_GROUP
        });
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    private ImmutableMap.Builder<String, Object> getQueryBuilder(final com.merakianalytics.orianna.types.data.match.MatchList data) {
        final ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        builder.put("recent", data.isRecent());
        if(data.getPlatform() != null) {
            builder.put("platform", Platform.withTag(data.getPlatform()));
        }
        if(data.getAccountId() != 0L) {
            builder.put("accountId", data.getAccountId());
        }
        if(data.getChampions() != null) {
            builder.put("champions", data.getChampions());
        }
        if(data.getEndIndex() != 0L) {
            builder.put("endIndex", data.getEndIndex());
        }
        if(endTime != null) {
            builder.put("endTime", endTime.getMillis());
        }
        if(data.getQueues() != null) {
            builder.put("queues", data.getQueues());
        }
        if(data.getSeasons() != null) {
            builder.put("seasons", data.getSeasons());
        }
        if(startIndex != null) {
            builder.put("beginIndex", startIndex);
        }
        if(data.getStartTime() != null) {
            builder.put("beginTime", data.getStartTime().getMillis());
        }
        return builder;
    }

    public Set<Queue> getQueues() {
        return queues.get();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public Set<Season> getSeasons() {
        return seasons.get();
    }

    public int getStartIndex() {
        return coreData.getStartIndex();
    }

    public DateTime getStartTime() {
        return coreData.getStartTime();
    }

    public Summoner getSummoner() {
        return summoner.get();
    }

    @Override
    public int indexOf(final Object o) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.isEmpty();
    }

    public boolean isRecent() {
        return coreData.isRecent();
    }

    @Override
    public Iterator<Match> iterator() {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.lastIndexOf(o);
    }

    @Override
    public ListIterator<Match> listIterator() {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.listIterator();
    }

    @Override
    public ListIterator<Match> listIterator(final int index) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.listIterator(index);
    }

    private com.merakianalytics.orianna.types.data.match.MatchList loadBatch() {
        if(complete) {
            return null;
        }

        synchronized(batchLoadLock) {
            if(complete) {
                return null;
            }

            if(coreData.isRecent()) {
                final ImmutableMap.Builder<String, Object> builder = getQueryBuilder(coreData);
                final com.merakianalytics.orianna.types.data.match.MatchList list =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.match.MatchList.class, builder.build());
                complete = true;
                return list;
            }

            final ImmutableMap.Builder<String, Object> builder = getQueryBuilder(coreData);
            final com.merakianalytics.orianna.types.data.match.MatchList list =
                Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.match.MatchList.class, builder.build());

            final int total = matchIterator.nextIndex() + list.size();

            // This was the last for this time period (or lack thereof) if it returned less than the max
            if(list.size() < list.getMaxSize()) {
                // We're done if we got the number of matches we wanted, or if we're done switching time periods so there are no more matches to be had.
                if(coreData.getEndIndex() > 0 && total >= coreData.getEndIndex() - coreData.getStartIndex()
                    || list.getStartTime().equals(coreData.getStartTime()) || list.getStartTime().isBefore(coreData.getStartTime())) {
                    complete = true;
                } else {
                    endTime = list.getStartTime();
                    if(startIndex != null) {
                        startIndex = Math.max(coreData.getStartIndex() - total, 0);
                    }
                }
            } else {
                startIndex = list.getEndIndex();
            }

            if(list.isEmpty()) {
                return loadBatch();
            }

            return list;
        }
    }

    @Override
    protected void loadCoreData(final String group) {
        switch(group) {
            case MATCH_HISTORY_LOAD_GROUP:
                final com.merakianalytics.orianna.types.data.match.MatchList list = loadBatch();
                list.setStartIndex(coreData.getStartIndex());
                list.setEndIndex(coreData.getEndIndex());
                list.setStartTime(coreData.getStartTime());
                list.setEndTime(coreData.getEndTime());
                coreData = list;
                matchIterator = coreData.listIterator(matchIterator.nextIndex());
                break;
            default:
                break;
        }
    }

    @Override
    public Match remove(final int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SearchableList<Match> search(final Object query) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.search(query);
    }

    @Override
    public SearchableList<Match> search(final Object query, final boolean streaming) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.search(query, streaming);
    }

    @Override
    public Match set(final int index, final Match element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.size();
    }

    @Override
    public List<Match> subList(final int fromIndex, final int toIndex) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        load(MATCH_HISTORY_LOAD_GROUP);
        return matches.toArray(a);
    }
}
