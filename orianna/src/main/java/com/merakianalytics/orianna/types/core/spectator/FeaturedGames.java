package com.merakianalytics.orianna.types.core.spectator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Duration;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public class FeaturedGames extends GhostObject.ListProxy<FeaturedGame, com.merakianalytics.orianna.types.data.spectator.FeaturedGame, com.merakianalytics.orianna.types.data.spectator.FeaturedGames> {
    public static class Builder {
        private Platform platform;

        private Builder() {}

        public FeaturedGames get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            return Orianna.getSettings().getPipeline().get(FeaturedGames.class, builder.build());
        }

        public Builder withPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Builder withRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }
    }

    public static class ManyBuilder {
        private final Iterable<Platform> platforms;
        private boolean streaming = false;

        private ManyBuilder(final Iterable<Platform> platforms) {
            this.platforms = platforms;
        }

        public SearchableList<FeaturedGames> get() {
            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platforms", platforms);

            final CloseableIterator<FeaturedGames> result = Orianna.getSettings().getPipeline().getMany(FeaturedGames.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public ManyBuilder streaming() {
            streaming = true;
            return this;
        }
    }

    private static final long serialVersionUID = 5550281909391512286L;

    public static Builder forPlatform(final Platform platform) {
        return new Builder().withPlatform(platform);
    }

    public static ManyBuilder forPlatforms(final Iterable<Platform> platforms) {
        return new ManyBuilder(platforms);
    }

    public static ManyBuilder forPlatforms(final Platform... platforms) {
        return new ManyBuilder(Arrays.asList(platforms));
    }

    public static Builder forRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public static ManyBuilder forRegions(final Iterable<Region> regions) {
        final List<Platform> platforms = new ArrayList<>();
        for(final Region region : regions) {
            platforms.add(region.getPlatform());
        }
        return new ManyBuilder(platforms);
    }

    public static ManyBuilder forRegions(final Region... regions) {
        final List<Platform> platforms = new ArrayList<>(regions.length);
        for(final Region region : regions) {
            platforms.add(region.getPlatform());
        }
        return new ManyBuilder(platforms);
    }

    public static FeaturedGames get() {
        return new Builder().get();
    }

    public FeaturedGames(final com.merakianalytics.orianna.types.data.spectator.FeaturedGames coreData) {
        super(coreData, 1);
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Duration getRefreshInterval() {
        load(LIST_PROXY_LOAD_GROUP);
        return coreData.getRefreshInterval();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case LIST_PROXY_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.spectator.FeaturedGames.class, builder.build());
                loadListProxyData(new Function<com.merakianalytics.orianna.types.data.spectator.FeaturedGame, FeaturedGame>() {
                    @Override
                    public FeaturedGame apply(final com.merakianalytics.orianna.types.data.spectator.FeaturedGame data) {
                        return new FeaturedGame(data);
                    }
                });
                break;
            default:
                break;
        }
    }
}
