package com.merakianalytics.orianna.types.core.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;

public class ShardStatus extends GhostObject<com.merakianalytics.orianna.types.data.status.ShardStatus> {
    public static class Builder {
        private Platform platform;

        private Builder() {}

        public ShardStatus get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            return Orianna.getSettings().getPipeline().get(ShardStatus.class, builder.build());
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

    private static final long serialVersionUID = -7141887712080838849L;
    public static final String SHARD_STATUS_LOAD_GROUP = "shard-status";

    public static Builder forPlatform(final Platform platform) {
        return new Builder().withPlatform(platform);
    }

    public static Builder forRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public static ShardStatus get() {
        return new Builder().get();
    }

    private final Supplier<List<String>> locales = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(SHARD_STATUS_LOAD_GROUP);
            if(coreData.getLocales() == null) {
                return null;
            }
            return Collections.unmodifiableList(coreData.getLocales());
        }
    });

    private final Supplier<List<Service>> services = Suppliers.memoize(new Supplier<List<Service>>() {
        @Override
        public List<Service> get() {
            load(SHARD_STATUS_LOAD_GROUP);
            if(coreData.getServices() == null) {
                return null;
            }
            final List<Service> services = new ArrayList<>(coreData.getServices().size());
            for(final com.merakianalytics.orianna.types.data.status.Service service : coreData.getServices()) {
                services.add(new Service(service));
            }
            return Collections.unmodifiableList(services);
        }
    });

    public ShardStatus(final com.merakianalytics.orianna.types.data.status.ShardStatus coreData) {
        super(coreData, 1);
    }

    public String getHostname() {
        load(SHARD_STATUS_LOAD_GROUP);
        return coreData.getHostname();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            SHARD_STATUS_LOAD_GROUP
        });
    }

    public List<String> getLocales() {
        return locales.get();
    }

    public String getName() {
        load(SHARD_STATUS_LOAD_GROUP);
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public String getRegionTag() {
        load(SHARD_STATUS_LOAD_GROUP);
        return coreData.getRegionTag();
    }

    public List<Service> getServices() {
        load(SHARD_STATUS_LOAD_GROUP);
        return services.get();
    }

    public String getSlug() {
        load(SHARD_STATUS_LOAD_GROUP);
        return coreData.getSlug();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case SHARD_STATUS_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.status.ShardStatus.class, builder.build());
                break;
            default:
                break;
        }
    }
}
