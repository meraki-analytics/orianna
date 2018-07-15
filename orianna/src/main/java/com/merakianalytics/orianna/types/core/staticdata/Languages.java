package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;

public class Languages extends GhostObject.ListProxy<String, String, com.merakianalytics.orianna.types.data.staticdata.Languages> {
    public static class Builder {
        private Platform platform;

        private Builder() {}

        public Languages get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);
            return Orianna.getSettings().getPipeline().get(Languages.class, builder.build());
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

    private static final long serialVersionUID = 243021836752375355L;

    public static Languages get() {
        return new Builder().get();
    }

    public static Builder withPlatform(final Platform platform) {
        return new Builder().withPlatform(platform);
    }

    public static Builder withRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public Languages(final com.merakianalytics.orianna.types.data.staticdata.Languages coreData) {
        super(coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.isEmpty()) {
            load(LIST_PROXY_LOAD_GROUP);
        }
        return !coreData.isEmpty();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            LIST_PROXY_LOAD_GROUP
        });
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
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
                final com.merakianalytics.orianna.types.data.staticdata.Languages data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Languages.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                loadListProxyData(Functions.<String> identity());
                break;
            default:
                break;
        }
    }
}
