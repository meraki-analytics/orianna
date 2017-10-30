package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;

public class LanguageStrings extends GhostObject.MapProxy<String, String, String, String, com.merakianalytics.orianna.types.data.staticdata.LanguageStrings> {
    public static class Builder {
        private Platform platform;
        private String version, locale;

        public Builder fromPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Builder fromRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }

        public LanguageStrings get() {
            if(version == null) {
                version = Orianna.getSettings().getCurrentVersion();
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            if(locale == null) {
                locale = platform.getDefaultLocale();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                                                                             .put("locale", locale);

            return Orianna.getSettings().getPipeline().get(LanguageStrings.class, builder.build());
        }

        public Builder onVersion(final String version) {
            this.version = version;
            return this;
        }

        public Builder withLocale(final String locale) {
            this.locale = locale;
            return this;
        }
    }

    private static final long serialVersionUID = -513204164367714008L;

    public static LanguageStrings get() {
        return new Builder().get();
    }

    public static Builder withLocale(final String locale) {
        return new Builder().withLocale(locale);
    }

    public static Builder withPlatform(final Platform platform) {
        return new Builder().fromPlatform(platform);
    }

    public static Builder withRegion(final Region region) {
        return new Builder().fromRegion(region);
    }

    public static Builder withVersion(final String version) {
        return new Builder().onVersion(version);
    }

    public LanguageStrings(final com.merakianalytics.orianna.types.data.staticdata.LanguageStrings coreData) {
        super(coreData, 1);
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public Platform getPlatform() {
        return coreData.getPlatform();
    }

    public Region getRegion() {
        return coreData.getPlatform().getRegion();
    }

    public String getType() {
        load(MAP_PROXY_LOAD_GROUP);
        return coreData.getType();
    }

    public String getVersion() {
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case MAP_PROXY_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", coreData.getPlatform());
                }
                if(coreData.getVersion() != null) {
                    builder.put("version", coreData.getVersion());
                }
                if(coreData.getLocale() != null) {
                    builder.put("locale", coreData.getLocale());
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.LanguageStrings.class, builder.build());
                loadMapProxyData();
                break;
            default:
                break;
        }
    }
}
