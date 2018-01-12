package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.UnmodifiableSearchableList;

public class Map extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.Map> {
    public static class Builder {
        private Integer id;
        private String name, version, locale;
        private Platform platform;

        private Builder(final int id) {
            this.id = id;
        }

        private Builder(final String name) {
            this.name = name;
        }

        public Map get() {
            if(name == null && id == null) {
                throw new IllegalStateException("Must set an ID or name for the Map!");
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

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale);

            if(id != null) {
                builder.put("id", id);
            } else {
                builder.put("name", name);
            }

            return Orianna.getSettings().getPipeline().get(Map.class, builder.build());
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

    public static final String MAP_LOAD_GROUP = "map";
    private static final long serialVersionUID = -3422815237683049727L;

    public static Builder named(final String name) {
        return new Builder(name);
    }

    public static Builder withId(final int id) {
        return new Builder(id);
    }

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            load(MAP_LOAD_GROUP);
            return new Image(coreData.getImage());
        }
    });

    private final Supplier<SearchableList<Item>> unpurchasableItems = Suppliers.memoize(new Supplier<SearchableList<Item>>() {
        @Override
        public SearchableList<Item> get() {
            load(MAP_LOAD_GROUP);
            return UnmodifiableSearchableList.of(Items.withIds(coreData.getUnpurchasableItems()).withPlatform(Platform.withTag(coreData.getPlatform()))
                .withVersion(coreData.getVersion()).withLocale(coreData.getLocale()).get());
        }
    });

    public Map(final com.merakianalytics.orianna.types.data.staticdata.Map coreData) {
        super(coreData, 1);
    }

    @Searchable(int.class)
    public int getId() {
        if(coreData.getId() == 0) {
            load(MAP_LOAD_GROUP);
        }
        return coreData.getId();
    }

    public Image getImage() {
        return image.get();
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public com.merakianalytics.orianna.types.common.Map getMap() {
        if(coreData.getId() == 0) {
            load(MAP_LOAD_GROUP);
        }
        return com.merakianalytics.orianna.types.common.Map.withId(coreData.getId());
    }

    @Searchable(String.class)
    public String getName() {
        if(coreData.getName() == null) {
            load(MAP_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public SearchableList<Item> getUnpurchaseableItems() {
        return unpurchasableItems.get();
    }

    public String getVersion() {
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case MAP_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getId() != 0) {
                    builder.put("id", coreData.getId());
                }
                if(coreData.getName() != null) {
                    builder.put("name", coreData.getName());
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

                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Map.class, builder.build());
                break;
            default:
                break;
        }
    }
}
