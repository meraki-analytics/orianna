package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.MasteryTree;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class Mastery extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.Mastery> {
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

        public Mastery get() {
            if(name == null && id == null) {
                throw new IllegalStateException("Must set an ID or name for the Item!");
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

            return Orianna.getSettings().getPipeline().get(Mastery.class, builder.build());
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

    public static final String MASTERY_LOAD_GROUP = "mastery";
    private static final long serialVersionUID = 3385648914780072452L;

    public static Builder named(final String name) {
        return new Builder(name);
    }

    public static Builder withId(final int id) {
        return new Builder(id);
    }

    private final Supplier<List<String>> descriptions = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(MASTERY_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getDescriptions());
        }
    });

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            load(MASTERY_LOAD_GROUP);
            return new Image(coreData.getImage());
        }
    });

    private final Supplier<Set<String>> includedData = Suppliers.memoize(new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            return Collections.unmodifiableSet(coreData.getIncludedData());
        }
    });

    private final Supplier<Mastery> prerequisite = Suppliers.memoize(new Supplier<Mastery>() {
        @Override
        public Mastery get() {
            load(MASTERY_LOAD_GROUP);
            return Mastery.withId(coreData.getPrerequisite()).withPlatform(Platform.valueOf(coreData.getPlatform())).withVersion(coreData.getVersion())
                .withLocale(coreData.getLocale()).get();
        }
    });

    private final Supplier<List<String>> sanitizedDescriptions = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(MASTERY_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getSanitizedDescriptions());
        }
    });

    public Mastery(final com.merakianalytics.orianna.types.data.staticdata.Mastery coreData) {
        super(coreData, 1);
    }

    public List<String> getDescriptions() {
        return descriptions.get();
    }

    @Searchable(int.class)
    public int getId() {
        if(coreData.getId() == 0) {
            load(MASTERY_LOAD_GROUP);
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
            load(MASTERY_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.valueOf(coreData.getPlatform());
    }

    public int getPoints() {
        load(MASTERY_LOAD_GROUP);
        return coreData.getPoints();
    }

    public Mastery getPrerequisite() {
        return prerequisite.get();
    }

    public Region getRegion() {
        return Platform.valueOf(coreData.getPlatform()).getRegion();
    }

    public List<String> getSanitizedDescriptions() {
        return sanitizedDescriptions.get();
    }

    public MasteryTree getTree() {
        load(MASTERY_LOAD_GROUP);
        return MasteryTree.valueOf(coreData.getTree());
    }

    public String getVersion() {
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case MASTERY_LOAD_GROUP:
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

                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Mastery.class, builder.build());
                break;
            default:
                break;
        }
    }
}
