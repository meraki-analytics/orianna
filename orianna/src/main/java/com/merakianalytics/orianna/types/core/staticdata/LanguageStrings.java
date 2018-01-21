package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;

public class LanguageStrings extends GhostObject.MapProxy<String, String, String, String, com.merakianalytics.orianna.types.data.staticdata.LanguageStrings> {
    public static class Builder {
        private Platform platform;
        private String version, locale;

        private Builder() {}

        public LanguageStrings get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            if(version == null) {
                version = Orianna.getSettings().getCurrentVersion(platform);
            }

            if(locale == null) {
                locale = Orianna.getSettings().getDefaultLocale();
                locale = locale == null ? platform.getDefaultLocale() : locale;
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale);

            return Orianna.getSettings().getPipeline().get(LanguageStrings.class, builder.build());
        }

        public Builder withLocale(final String locale) {
            this.locale = locale;
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

        public Builder withVersion(final String version) {
            this.version = version;
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
        return new Builder().withPlatform(platform);
    }

    public static Builder withRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public static Builder withVersion(final String version) {
        return new Builder().withVersion(version);
    }

    public LanguageStrings(final com.merakianalytics.orianna.types.data.staticdata.LanguageStrings coreData) {
        super(coreData, 1);
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            MAP_PROXY_LOAD_GROUP
        });
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
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
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
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
