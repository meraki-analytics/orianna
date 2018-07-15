package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class ReforgedRune extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.ReforgedRune> {
    public static class Builder {
        private Integer id;
        private String name, key, version, locale;
        private Platform platform;

        private Builder(final int id) {
            this.id = id;
        }

        private Builder(final String name, final boolean isName) {
            if(isName) {
                this.name = name;
            } else {
                key = name;
            }
        }

        public ReforgedRune get() {
            if(name == null && id == null && key == null) {
                throw new IllegalStateException("Must set an ID, name, or key for the ReforgedRune!");
            }

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

            if(id != null) {
                builder.put("id", id);
            } else if(key != null) {
                builder.put("key", key);
            } else {
                builder.put("name", name);
            }

            return Orianna.getSettings().getPipeline().get(ReforgedRune.class, builder.build());
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

    public static final String REFORGED_RUNE_LOAD_GROUP = "reforged-rune";
    private static final long serialVersionUID = -5938555056954804134L;

    public static Builder named(final String name) {
        return new Builder(name, true);
    }

    public static Builder withId(final int id) {
        return new Builder(id);
    }

    public static Builder withKey(final String key) {
        return new Builder(key, false);
    }

    public ReforgedRune(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRune coreData) {
        super(coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.getShortDescription() == null) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getShortDescription() != null;
    }

    @Searchable(int.class)
    public int getId() {
        if(coreData.getId() == 0) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getId();
    }

    public String getImage() {
        if(coreData.getImage() == null) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getImage();
    }

    @Searchable(String.class)
    public String getKey() {
        if(coreData.getKey() == null) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getKey();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            REFORGED_RUNE_LOAD_GROUP
        });
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public String getLongDescription() {
        if(coreData.getLongDescription() == null) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getLongDescription();
    }

    @Searchable(String.class)
    public String getName() {
        if(coreData.getName() == null) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public RunePath getPath() {
        if(coreData.getPath() == 0) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return RunePath.withId(coreData.getPath());
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public String getShortDescription() {
        if(coreData.getShortDescription() == null) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getShortDescription();
    }

    public int getSlot() {
        if(coreData.getSlot() == 0) {
            load(REFORGED_RUNE_LOAD_GROUP);
        }
        return coreData.getSlot();
    }

    public String getVersion() {
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case REFORGED_RUNE_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getId() != 0) {
                    builder.put("id", coreData.getId());
                }
                if(coreData.getName() != null) {
                    builder.put("name", coreData.getName());
                }
                if(coreData.getKey() != null) {
                    builder.put("key", coreData.getKey());
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

                final com.merakianalytics.orianna.types.data.staticdata.ReforgedRune data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.ReforgedRune.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                break;
            default:
                break;
        }
    }
}
