package com.merakianalytics.orianna.types.core.status;

import java.util.ArrayList;
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
        private final Platform platform;

        private Builder(final Platform platform) {
            this.platform = platform;
        }

        private Builder(final Region region) {
            platform = region.getPlatform();
        }

        public ShardStatus get() {
            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            return Orianna.getSettings().getPipeline().get(ShardStatus.class, builder.build());
        }
    }

    private static final long serialVersionUID = -7141887712080838849L;
    public static final String SHARD_STATUS_LOAD_GROUP = "shard-status";

    public static ShardStatus forPlatform(final Platform platform) {
        return new Builder(platform).get();
    }

    public static ShardStatus forRegion(final Region region) {
        return new Builder(region).get();
    }

    private final Supplier<List<String>> locales = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(SHARD_STATUS_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getLocales());
        }
    });

    private final Supplier<List<Service>> services = Suppliers.memoize(new Supplier<List<Service>>() {
        @Override
        public List<Service> get() {
            load(SHARD_STATUS_LOAD_GROUP);
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

    public List<Service> getServices() {
        load(SHARD_STATUS_LOAD_GROUP);
        return services.get();
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
