package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableListWrapper;

public class Item extends GhostObject<com.merakianalytics.orianna.types.data.staticdata.Item> {
    public static class Builder {
        private Integer id;
        private Set<String> includedData;
        private String name, version, locale;
        private Platform platform;

        public Builder fromPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Builder fromRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }

        public Item get() {
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

            return Orianna.getSettings().getPipeline().get(Item.class, builder.build());
        }

        public Builder named(final String name) {
            this.name = name;
            return this;
        }

        public Builder onVersion(final String version) {
            this.version = version;
            return this;
        }

        public Builder withId(final int id) {
            this.id = id;
            return this;
        }

        public Builder withIncludedData(final Set<String> includedData) {
            this.includedData = includedData;
            return this;
        }

        public Builder withLocale(final String locale) {
            this.locale = locale;
            return this;
        }
    }

    public static final String ITEM_LOAD_GROUP = "item";
    private static final long serialVersionUID = 307765113960787815L;

    public static Builder named(final String name) {
        return new Builder().named(name);
    }

    public static Builder withId(final int id) {
        return new Builder().withId(id);
    }

    private final Supplier<SearchableList<Item>> buildsFrom = Suppliers.memoize(new Supplier<SearchableList<Item>>() {
        @Override
        public SearchableList<Item> get() {
            load(ITEM_LOAD_GROUP);
            final List<Item> buildsFrom = new ArrayList<>(coreData.getBuildsFrom().size());
            for(final Integer id : coreData.getBuildsFrom()) {
                buildsFrom.add(Item.withId(id).fromPlatform(coreData.getPlatform()).onVersion(coreData.getVersion()).withLocale(coreData.getLocale()).get());
            }
            return SearchableListWrapper.of(Collections.unmodifiableList(buildsFrom));
        }
    });

    private final Supplier<SearchableList<Item>> buildsInto = Suppliers.memoize(new Supplier<SearchableList<Item>>() {
        @Override
        public SearchableList<Item> get() {
            load(ITEM_LOAD_GROUP);
            final List<Item> buildsInto = new ArrayList<>(coreData.getBuildsInto().size());
            for(final Integer id : coreData.getBuildsInto()) {
                buildsInto.add(Item.withId(id).fromPlatform(coreData.getPlatform()).onVersion(coreData.getVersion()).withLocale(coreData.getLocale()).get());
            }
            return SearchableListWrapper.of(Collections.unmodifiableList(buildsInto));
        }
    });

    private final Supplier<Map<String, String>> effects = Suppliers.memoize(new Supplier<Map<String, String>>() {
        @Override
        public Map<String, String> get() {
            load(ITEM_LOAD_GROUP);
            return Collections.unmodifiableMap(coreData.getEffects());
        }
    });

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            load(ITEM_LOAD_GROUP);
            return new Image(coreData.getImage());
        }
    });

    private final Supplier<Set<String>> includedData = Suppliers.memoize(new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            return Collections.unmodifiableSet(coreData.getIncludedData());
        }
    });

    private final Supplier<Set<String>> keywords = Suppliers.memoize(new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            load(ITEM_LOAD_GROUP);
            return Collections.unmodifiableSet(coreData.getKeywords());
        }
    });

    private final Supplier<Set<com.merakianalytics.orianna.types.common.Map>> maps = Suppliers.memoize(
        new Supplier<Set<com.merakianalytics.orianna.types.common.Map>>() {
            @Override
            public Set<com.merakianalytics.orianna.types.common.Map> get() {
                load(ITEM_LOAD_GROUP);
                return Collections.unmodifiableSet(coreData.getMaps());
            }
        });

    private final Supplier<Champion> requiredChampion = Suppliers.memoize(new Supplier<Champion>() {
        @Override
        public Champion get() {
            // TODO: Champion by key
            load(ITEM_LOAD_GROUP);
            return Champion.named(coreData.getRequiredChampionKey()).fromPlatform(coreData.getPlatform()).onVersion(coreData.getVersion())
                           .withLocale(coreData.getLocale()).get();
        }
    });

    private final Supplier<ItemStats> stats = Suppliers.memoize(new Supplier<ItemStats>() {
        @Override
        public ItemStats get() {
            load(ITEM_LOAD_GROUP);
            return new ItemStats(coreData.getStats());
        }
    });

    private final Supplier<List<String>> tags = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(ITEM_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getTags());
        }
    });

    public Item(final com.merakianalytics.orianna.types.data.staticdata.Item coreData) {
        super(coreData, 1);
    }

    public int getBasePrice() {
        load(ITEM_LOAD_GROUP);
        return coreData.getBasePrice();
    }

    public SearchableList<Item> getBuildsFrom() {
        return buildsFrom.get();
    }

    public SearchableList<Item> getBuildsInto() {
        return buildsInto.get();
    }

    public String getDescription() {
        load(ITEM_LOAD_GROUP);
        return coreData.getDescription();
    }

    public Map<String, String> getEffects() {
        return effects.get();
    }

    public String getGroup() {
        load(ITEM_LOAD_GROUP);
        return coreData.getGroup();
    }

    @Searchable(int.class)
    public int getId() {
        if(coreData.getId() == 0) {
            load(ITEM_LOAD_GROUP);
        }
        return coreData.getId();
    }

    public Image getImage() {
        return image.get();
    }

    public Set<String> getIncludedData() {
        return includedData.get();
    }

    public Set<String> getKeywords() {
        return keywords.get();
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public Set<com.merakianalytics.orianna.types.common.Map> getMaps() {
        return maps.get();
    }

    public int getMaxStacks() {
        load(ITEM_LOAD_GROUP);
        return coreData.getMaxStacks();
    }

    @Searchable(String.class)
    public String getName() {
        if(coreData.getName() == null) {
            load(ITEM_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public String getPlaintext() {
        load(ITEM_LOAD_GROUP);
        return coreData.getPlaintext();
    }

    public Platform getPlatform() {
        return coreData.getPlatform();
    }

    public Region getRegion() {
        return coreData.getPlatform().getRegion();
    }

    public Champion getRequiredChampion() {
        return requiredChampion.get();
    }

    public String getSanitizedDescription() {
        load(ITEM_LOAD_GROUP);
        return coreData.getSanitizedDescription();
    }

    public int getSellPrice() {
        load(ITEM_LOAD_GROUP);
        return coreData.getSellPrice();
    }

    public int getSource() {
        load(ITEM_LOAD_GROUP);
        return coreData.getSource();
    }

    public ItemStats getStats() {
        return stats.get();
    }

    public List<String> getTags() {
        return tags.get();
    }

    public int getTier() {
        load(ITEM_LOAD_GROUP);
        return coreData.getTier();
    }

    public int getTotalPrice() {
        load(ITEM_LOAD_GROUP);
        return coreData.getTotalPrice();
    }

    public String getVersion() {
        return coreData.getVersion();
    }

    public boolean isConsumed() {
        load(ITEM_LOAD_GROUP);
        return coreData.isConsumed();
    }

    public boolean isConsumedWhenFull() {
        load(ITEM_LOAD_GROUP);
        return coreData.isConsumedWhenFull();
    }

    public boolean isHiddenFromAll() {
        load(ITEM_LOAD_GROUP);
        return coreData.isHiddenFromAll();
    }

    public boolean isInStore() {
        load(ITEM_LOAD_GROUP);
        return coreData.isInStore();
    }

    public boolean isPurchasable() {
        load(ITEM_LOAD_GROUP);
        return coreData.isPurchasable();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case ITEM_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getId() != 0) {
                    builder.put("id", coreData.getId());
                }
                if(coreData.getName() != null) {
                    builder.put("name", coreData.getName());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", coreData.getPlatform());
                }
                if(coreData.getVersion() != null) {
                    builder.put("version", coreData.getVersion());
                }
                if(coreData.getLocale() != null) {
                    builder.put("locale", coreData.getLocale());
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Item.class, builder.build());
                break;
            default:
                break;
        }
    }
}
