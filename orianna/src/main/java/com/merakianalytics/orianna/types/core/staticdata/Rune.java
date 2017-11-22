package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.RuneType;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class Rune extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.Rune> {
    public static class Builder {
        private Integer id;
        private Set<String> includedData;
        private String name, version, locale;
        private Platform platform;

        private Builder(final int id) {
            this.id = id;
        }

        private Builder(final String name) {
            this.name = name;
        }

        public Rune get() {
            if(name == null && id == null) {
                throw new IllegalStateException("Must set an ID or name for the Rune!");
            }

            if(version == null) {
                version = Orianna.getSettings().getCurrentVersion();
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            if(locale == null) {
                locale = platform.getDefaultLocale();
            }

            if(includedData == null) {
                includedData = ImmutableSet.of("all");
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale).put("includedData", includedData);

            if(id != null) {
                builder.put("id", id);
            } else {
                builder.put("name", name);
            }

            return Orianna.getSettings().getPipeline().get(Rune.class, builder.build());
        }

        public Builder withIncludedData(final Set<String> includedData) {
            this.includedData = includedData;
            return this;
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

    public static final String RUNE_LOAD_GROUP = "rune";

    private static final long serialVersionUID = 2420355663284372069L;

    public static Builder named(final String name) {
        return new Builder(name);
    }

    public static Builder withId(final int id) {
        return new Builder(id);
    }

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            load(RUNE_LOAD_GROUP);
            return new Image(coreData.getImage());
        }
    });

    private final Supplier<Set<String>> includedData = Suppliers.memoize(new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            return Collections.unmodifiableSet(coreData.getIncludedData());
        }
    });

    private final Supplier<RuneStats> stats = Suppliers.memoize(new Supplier<RuneStats>() {
        @Override
        public RuneStats get() {
            load(RUNE_LOAD_GROUP);
            return new RuneStats(coreData.getStats());
        }
    });

    private final Supplier<List<String>> tags = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(RUNE_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getTags());
        }
    });

    public Rune(final com.merakianalytics.orianna.types.data.staticdata.Rune coreData) {
        super(coreData, 1);
    }

    public String getDescription() {
        load(RUNE_LOAD_GROUP);
        return coreData.getDescription();
    }

    @Searchable(int.class)
    public int getId() {
        if(coreData.getId() == 0) {
            load(RUNE_LOAD_GROUP);
        }
        return coreData.getId();
    }

    public Image getImage() {
        return image.get();
    }

    public Set<String> getIncludedData() {
        return includedData.get();
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    @Searchable(String.class)
    public String getName() {
        if(coreData.getName() == null) {
            load(RUNE_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.valueOf(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.valueOf(coreData.getPlatform()).getRegion();
    }

    public String getSanitizedDescription() {
        load(RUNE_LOAD_GROUP);
        return coreData.getSanitizedDescription();
    }

    public RuneStats getStats() {
        return stats.get();
    }

    public List<String> getTags() {
        return tags.get();
    }

    public int getTier() {
        load(RUNE_LOAD_GROUP);
        return coreData.getTier();
    }

    public RuneType getType() {
        load(RUNE_LOAD_GROUP);
        return RuneType.withColor(coreData.getType());
    }

    public String getVersion() {
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case RUNE_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getId() != 0) {
                    builder.put("id", coreData.getId());
                }
                if(coreData.getName() != null) {
                    builder.put("name", coreData.getName());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.valueOf(coreData.getPlatform()));
                }
                if(coreData.getVersion() != null) {
                    builder.put("version", coreData.getVersion());
                }
                if(coreData.getLocale() != null) {
                    builder.put("locale", coreData.getLocale());
                }
                if(coreData.getIncludedData() != null) {
                    builder.put("includedData", coreData.getIncludedData());
                }

                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Rune.class, builder.build());
                break;
            default:
                break;
        }
    }
}
