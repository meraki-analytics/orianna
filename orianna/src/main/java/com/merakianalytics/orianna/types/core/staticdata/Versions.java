package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public class Versions extends GhostObject.ListProxy<String, String, com.merakianalytics.orianna.types.data.staticdata.Versions> {
    public static class Builder {
        private Platform platform;

        private Builder() {}

        public Versions get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
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

    public static class ManyBuilder {
        private final Iterable<Platform> platforms;
        private boolean streaming = false;

        private ManyBuilder(final Iterable<Platform> platforms) {
            this.platforms = platforms;
        }

        public SearchableList<Versions> get() {
            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platforms", platforms);

            final CloseableIterator<Versions> result = Orianna.getSettings().getPipeline().getMany(Versions.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public ManyBuilder streaming() {
            streaming = true;
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

    public static ManyBuilder withPlatforms(final Iterable<Platform> platforms) {
        return new ManyBuilder(platforms);
    }

    public static ManyBuilder withPlatforms(final Platform... platforms) {
        return new ManyBuilder(Arrays.asList(platforms));
    }

    public static Builder withRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public static ManyBuilder withRegions(final Iterable<Region> regions) {
        final List<Platform> platforms = new ArrayList<>();
        for(final Region region : regions) {
            platforms.add(region.getPlatform());
        }
        return new ManyBuilder(platforms);
    }

    public static ManyBuilder withRegions(final Region... regions) {
        final List<Platform> platforms = new ArrayList<>(regions.length);
        for(final Region region : regions) {
            platforms.add(region.getPlatform());
        }
        return new ManyBuilder(platforms);
    }

    public Versions(final com.merakianalytics.orianna.types.data.staticdata.Versions coreData) {
        super(coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.isEmpty()) {
            load(LIST_PROXY_LOAD_GROUP);
        }
        return !coreData.isEmpty();
    }

    public String getBestMatch(final String targetVersion) {
        String[] parts = targetVersion.split("\\.");

        final int targetMajor = Integer.parseInt(parts[0]);
        final int targetMinor = Integer.parseInt(parts[1]);

        for(final String version : this) {
            if(targetVersion.equals(version)) {
                return version;
            }

            parts = version.split("\\.");
            if(parts.length != 3) {
                continue;
            }

            final int major = Integer.parseInt(parts[0]);
            if(major > targetMajor) {
                continue;
            }

            final int minor = Integer.parseInt(parts[1]);
            if(minor > targetMinor) {
                continue;
            }

            // We're going to ignore the patch version and just go with the most recent for the major/minor.
            // As I understand it, the ddragon patch version numbers don't necessarily align with the game server version exactly, and there have been a lot of
            // inconsistencies in the patch versioning.

            return version; // Since the versions are ordered, this is the first version <= the target.
        }
        return null;
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
                final com.merakianalytics.orianna.types.data.staticdata.Versions data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Versions.class, builder.build());
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
