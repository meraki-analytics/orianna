package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Collections;
import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;

public class Realm extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.Realm> {
    public static class Builder {
        private Platform platform;

        public Realm get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);
            return Orianna.getSettings().getPipeline().get(Realm.class, builder.build());
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

    public static final String REALM_LOAD_GROUP = "realm";
    private static final long serialVersionUID = 232469766173551775L;

    public static Realm get() {
        return new Builder().get();
    }

    public static Builder withPlatform(final Platform platform) {
        return new Builder().withPlatform(platform);
    }

    public static Builder withRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    private final Supplier<Map<String, String>> latestVersions = Suppliers.memoize(new Supplier<Map<String, String>>() {
        @Override
        public Map<String, String> get() {
            load(REALM_LOAD_GROUP);
            return Collections.unmodifiableMap(coreData.getLatestVersions());
        }
    });

    public Realm(final com.merakianalytics.orianna.types.data.staticdata.Realm coreData) {
        super(coreData, 1);
    }

    public String getCDN() {
        load(REALM_LOAD_GROUP);
        return coreData.getCDN();
    }

    public String getCSSVersion() {
        load(REALM_LOAD_GROUP);
        return coreData.getCSSVersion();
    }

    public String getDefaultLocale() {
        load(REALM_LOAD_GROUP);
        return coreData.getDefaultLocale();
    }

    public String getLatestDataDragon() {
        load(REALM_LOAD_GROUP);
        return coreData.getLatestDataDragon();
    }

    public Map<String, String> getLatestVersions() {
        return latestVersions.get();
    }

    public String getLegacyMode() {
        load(REALM_LOAD_GROUP);
        return coreData.getLegacyMode();
    }

    public int getMaxProfileIconId() {
        load(REALM_LOAD_GROUP);
        return coreData.getMaxProfileIconId();
    }

    public Platform getPlatform() {
        return coreData.getPlatform();
    }

    public Region getRegion() {
        return coreData.getPlatform().getRegion();
    }

    public String getStore() {
        load(REALM_LOAD_GROUP);
        return coreData.getStore();
    }

    public String getVersion() {
        load(REALM_LOAD_GROUP);
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case REALM_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", coreData.getPlatform());
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Realm.class, builder.build());
                break;
            default:
                break;
        }
    }

}
