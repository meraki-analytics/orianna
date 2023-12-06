package com.merakianalytics.orianna.types.core.championmastery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public abstract class ChampionMasteryScores {
    public static class Builder {
        private boolean streaming = false;
        private final Iterable<Summoner> summoners;

        private Builder(final Iterable<Summoner> summoners) {
            this.summoners = summoners;
        }

        public SearchableList<ChampionMasteryScore> get() {
            final List<String> ids = new ArrayList<>();
            final Iterator<Summoner> iterator = summoners.iterator();
            Summoner summoner = iterator.next();

            if(summoner == null) {
                return SearchableLists.from(Collections.<ChampionMasteryScore> emptyList());
            }

            final Platform platform = summoner.getPlatform();
            ids.add(summoner.getPuuid());
            while(iterator.hasNext()) {
                summoner = iterator.next();

                if(platform != summoner.getPlatform()) {
                    throw new IllegalArgumentException("All summoners must be from the same platform/region!");
                }
                ids.add(summoner.getPuuid());
            }

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", platform).put("puuids", ids);

            final CloseableIterator<ChampionMasteryScore> result =
                Orianna.getSettings().getPipeline().getMany(ChampionMasteryScore.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public Builder streaming() {
            streaming = true;
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
