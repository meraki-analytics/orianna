package com.merakianalytics.orianna.types.core.spectator;

import org.joda.time.Duration;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;

public class FeaturedGames extends GhostObject.ListProxy<FeaturedGame, com.merakianalytics.orianna.types.data.spectator.FeaturedGame, com.merakianalytics.orianna.types.data.spectator.FeaturedGames> {
    public static class Builder {
        private final Platform platform;

        private Builder(final Platform platform) {
            this.platform = platform;
        }

        private Builder(final Region region) {
            platform = region.getPlatform();
        }

        public FeaturedGames get() {
            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            return Orianna.getSettings().getPipeline().get(FeaturedGames.class, builder.build());
        }
    }

    private static final long serialVersionUID = 5550281909391512286L;

    public static FeaturedGames forPlatform(final Platform platform) {
        return new Builder(platform).get();
    }

    public static FeaturedGames forRegion(final Region region) {
        return new Builder(region).get();
    }

    public FeaturedGames(final com.merakianalytics.orianna.types.data.spectator.FeaturedGames coreData) {
        super(coreData, 1, new Function<com.merakianalytics.orianna.types.data.spectator.FeaturedGame, FeaturedGame>() {
            @Override
            public FeaturedGame apply(final com.merakianalytics.orianna.types.data.spectator.FeaturedGame data) {
                return new FeaturedGame(data);
            }
        });
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
                loadListProxyData();
                break;
            default:
                break;
        }
    }
}
