package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;

public class Versions extends GhostObject.ListProxy<String, String, com.merakianalytics.orianna.types.data.staticdata.Versions> {
    public static class Builder {
        private Platform platform;

        private Builder() {}

        public Versions get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);
            return Orianna.getSettings().getPipeline().get(Versions.class, builder.build());
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

    private static final long serialVersionUID = 6003302668600909723L;

    public static Versions get() {
        return new Builder().get();
    }

    public static Builder withPlatform(final Platform platform) {
        return new Builder().withPlatform(platform);
    }

    public static Builder withRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public Versions(final com.merakianalytics.orianna.types.data.staticdata.Versions coreData) {
        super(coreData, 1);
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
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Versions.class, builder.build());
                loadListProxyData(Functions.<String> identity());
                break;
            default:
                break;
        }
    }

    public String truncate(final String targetVersion) {
        String[] parts = targetVersion.split("\\.");
        final int targetMajor = Integer.parseInt(parts[0]);
        final int targetMinor = Integer.parseInt(parts[1]);
        final int targetPatch = Integer.parseInt(parts[2]);

        for(int i = 0; i < size(); i++) {
            parts = this.get(i).split("\\.");
            final int major = Integer.parseInt(parts[0]);
            if(major > targetMajor) {
                continue;
            }

            final int minor = Integer.parseInt(parts[1]);
            if(minor > targetMinor) {
                continue;
            }

            final int patch = Integer.parseInt(parts[2]);
            if(patch > targetPatch) {
                continue;
            }

            return this.get(i); // Since the versions are ordered, this is the first version <= the target.
        }
        return null;
    }
}
