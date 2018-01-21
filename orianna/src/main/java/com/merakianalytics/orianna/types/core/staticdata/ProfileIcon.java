package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class ProfileIcon extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.ProfileIcon> {
    public static class Builder {
        private final int id;
        private Platform platform;
        private String version, locale;

        private Builder(final int id) {
            this.id = id;
        }

        public ProfileIcon get() {
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

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("id", id).put("platform", platform).put("version", version)
                    .put("locale", locale);

            return Orianna.getSettings().getPipeline().get(ProfileIcon.class, builder.build());
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
    public static final String PROFILE_ICON_LOAD_GROUP = "profile-icon";
    private static final long serialVersionUID = -3350437868875062008L;

    public static Builder withId(final int id) {
        return new Builder(id);
    }

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            load(PROFILE_ICON_LOAD_GROUP);
            return new Image(coreData.getImage());
        }
    });

    public ProfileIcon(final com.merakianalytics.orianna.types.data.staticdata.ProfileIcon coreData) {
        super(coreData, 1);
    }

    @Searchable(int.class)
    public int getId() {
        return coreData.getId();
    }

    public Image getImage() {
        return image.get();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            PROFILE_ICON_LOAD_GROUP
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

    public String getVersion() {
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case PROFILE_ICON_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getId() != 0) {
                    builder.put("id", coreData.getId());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                if(coreData.getVersion() != null) {
                    builder.put("version", coreData.getVersion());
                }
                if(coreData.getLocale() != null) {
                    builder.put("locale", coreData.getLocale());
                }

                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.ProfileIcon.class, builder.build());
                break;
            default:
                break;
        }
    }
}
