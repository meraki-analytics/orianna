package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.GhostObject;

public class Patch extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.Patch> {
    public static class Builder {
        private String name;
        private Platform platform;

        private Builder() {}

        public Patch get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);
            if(name != null) {
                builder.put("name", name);
            }

            return Orianna.getSettings().getPipeline().get(Patch.class, builder.build());
        }

        public Builder named(final String name) {
            this.name = name;
            return this;
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

    public static final String PATCH_LOAD_GROUP = "patch";
    private static final long serialVersionUID = -3616467806524426695L;

    public static Patch get() {
        return new Builder().get();
    }

    public static Builder named(final String name) {
        return new Builder().named(name);
    }

    public static Builder withPlatform(final Platform platform) {
        return new Builder().withPlatform(platform);
    }

    public static Builder withRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public Patch(final com.merakianalytics.orianna.types.data.staticdata.Patch coreData) {
        super(coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.getSeason() == -1) {
            load(PATCH_LOAD_GROUP);
        }
        return coreData.getSeason() != -1;
    }

    public DateTime getEnd() {
        if(coreData.getEnd() == null) {
            load(PATCH_LOAD_GROUP);
        }
        return coreData.getEnd();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            PATCH_LOAD_GROUP
        });
    }

    public String getName() {
        if(coreData.getName() == null) {
            load(PATCH_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public Season getSeason() {
        if(coreData.getSeason() == -1) {
            load(PATCH_LOAD_GROUP);
        }
        return Season.withId(coreData.getSeason());
    }

    public DateTime getStart() {
        if(coreData.getStart() == null) {
            load(PATCH_LOAD_GROUP);
        }
        return coreData.getStart();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case PATCH_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getName() != null) {
                    builder.put("name", coreData.getName());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }

                final com.merakianalytics.orianna.types.data.staticdata.Patch data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Patch.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                break;
            default:
                break;
        }
    }

}
