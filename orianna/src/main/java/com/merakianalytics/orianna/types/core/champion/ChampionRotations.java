package com.merakianalytics.orianna.types.core.champion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public abstract class ChampionRotations {
    public static class Builder {
        private final Iterable<Platform> platforms;
        private boolean streaming = false;

        private Builder(final Iterable<Platform> platforms) {
            this.platforms = platforms;
        }

        public SearchableList<ChampionRotation> get() {
            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platforms", platforms);

            final CloseableIterator<ChampionRotation> result = Orianna.getSettings().getPipeline().getMany(ChampionRotation.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public Builder streaming() {
            streaming = true;
            return this;
        }
    }

    public static Builder withPlatforms(final Iterable<Platform> platforms) {
        return new Builder(platforms);
    }

    public static Builder withPlatforms(final Platform... platforms) {
        return new Builder(Arrays.asList(platforms));
    }

    public static Builder withRegions(final Iterable<Region> regions) {
        final List<Platform> platforms = new ArrayList<>();
        for(final Region region : regions) {
            platforms.add(region.getPlatform());
        }
        return new Builder(platforms);
    }

    public static Builder withRegions(final Region... regions) {
        final List<Platform> platforms = new ArrayList<>(regions.length);
        for(final Region region : regions) {
            platforms.add(region.getPlatform());
        }
        return new Builder(platforms);
    }
}
