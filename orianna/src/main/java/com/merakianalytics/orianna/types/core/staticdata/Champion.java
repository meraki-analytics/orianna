package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;
import com.merakianalytics.orianna.types.data.CoreData;
import com.merakianalytics.orianna.types.data.champion.ChampionStatus;

public class Champion extends GhostObject<ChampionData> {
    public static class Builder {
        private Integer id;
        private Set<String> includedData;
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

        public Champion get() {
            if(name == null && id == null && key == null) {
                throw new IllegalStateException("Must set an ID, name, or key for the Champion!");
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

            if(includedData == null) {
                includedData = ImmutableSet.of("all");
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale).put("includedData", includedData);

            if(id != null) {
                builder.put("id", id);
            } else if(key != null) {
                builder.put("key", key);
            } else {
                builder.put("name", name);
            }

            return Orianna.getSettings().getPipeline().get(Champion.class, builder.build());
        }

        public Builder withIncludedData(final Iterable<String> includedData) {
            this.includedData = Sets.newHashSet(includedData);
            return this;
        }

        public Builder withIncludedData(final String... includedData) {
            this.includedData = Sets.newHashSet(includedData);
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

    public static class ChampionData extends CoreData {
        private static final long serialVersionUID = 8024144237694671276L;
        private com.merakianalytics.orianna.types.data.staticdata.Champion champion = new com.merakianalytics.orianna.types.data.staticdata.Champion();
        private ChampionStatus status = new ChampionStatus();

        /**
         * @return the champion
         */
        public com.merakianalytics.orianna.types.data.staticdata.Champion getChampion() {
            return champion;
        }

        /**
         * @return the status
         */
        public ChampionStatus getStatus() {
            return status;
        }

        /**
         * @param champion
         *        the champion to set
         */
        public void setChampion(final com.merakianalytics.orianna.types.data.staticdata.Champion champion) {
            this.champion = champion;
        }

        /**
         * @param status
         *        the status to set
         */
        public void setStatus(final ChampionStatus status) {
            this.status = status;
        }
    }

    public static final String CHAMPION_LOAD_GROUP = "champion";
    private static final long serialVersionUID = -2685748491353023270L;
    public static final String STATUS_LOAD_GROUP = "status";

    public static Builder named(final String name) {
        return new Builder(name, true);
    }

    public static Builder withId(final int id) {
        return new Builder(id);
    }

    public static Builder withKey(final String key) {
        return new Builder(key, false);
    }

    private final Supplier<List<String>> allyTips = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(CHAMPION_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getChampion().getAllyTips());
        }
    });

    private final Supplier<List<String>> enemyTips = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(CHAMPION_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getChampion().getEnemyTips());
        }
    });

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            load(CHAMPION_LOAD_GROUP);
            return new Image(coreData.getChampion().getImage());
        }
    });

    private final Supplier<Set<String>> includedData = Suppliers.memoize(new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            return Collections.unmodifiableSet(coreData.getChampion().getIncludedData());
        }
    });

    private final Supplier<Passive> passive = Suppliers.memoize(new Supplier<Passive>() {
        @Override
        public Passive get() {
            load(CHAMPION_LOAD_GROUP);
            return new Passive(coreData.getChampion().getPassive());
        }
    });

    private final Supplier<SearchableList<RecommendedItems>> recommendedItems = Suppliers.memoize(new Supplier<SearchableList<RecommendedItems>>() {
        @Override
        public SearchableList<RecommendedItems> get() {
            load(CHAMPION_LOAD_GROUP);
            final List<RecommendedItems> recommendedItems = new ArrayList<>(coreData.getChampion().getRecommendedItems().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.RecommendedItems items : coreData.getChampion().getRecommendedItems()) {
                recommendedItems.add(new RecommendedItems(items));
            }
            return SearchableLists.unmodifiableFrom(recommendedItems);
        }
    });

    private final Supplier<SearchableList<Skin>> skins = Suppliers.memoize(new Supplier<SearchableList<Skin>>() {
        @Override
        public SearchableList<Skin> get() {
            load(CHAMPION_LOAD_GROUP);
            final List<Skin> skins = new ArrayList<>(coreData.getChampion().getSkins().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.Skin skin : coreData.getChampion().getSkins()) {
                skins.add(new Skin(skin));
            }
            return SearchableLists.unmodifiableFrom(skins);
        }
    });

    private final Supplier<SearchableList<ChampionSpell>> spells = Suppliers.memoize(new Supplier<SearchableList<ChampionSpell>>() {
        @Override
        public SearchableList<ChampionSpell> get() {
            load(CHAMPION_LOAD_GROUP);
            final List<ChampionSpell> spells = new ArrayList<>(coreData.getChampion().getSpells().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.ChampionSpell spell : coreData.getChampion().getSpells()) {
                spells.add(new ChampionSpell(spell));
            }
            return SearchableLists.unmodifiableFrom(spells);
        }
    });

    private final Supplier<ChampionStats> stats = Suppliers.memoize(new Supplier<ChampionStats>() {
        @Override
        public ChampionStats get() {
            load(CHAMPION_LOAD_GROUP);
            return new ChampionStats(coreData.getChampion().getStats());
        }
    });

    private final Supplier<List<String>> tags = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            load(CHAMPION_LOAD_GROUP);
            return Collections.unmodifiableList(coreData.getChampion().getTags());
        }
    });

    public Champion(final ChampionData coreData) {
        super(coreData, 2);
    }

    public List<String> getAllyTips() {
        return allyTips.get();
    }

    public String getBlurb() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getBlurb();
    }

    public int getDefenseRating() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getDefenseRating();
    }

    public int getDifficultyRating() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getDifficultyRating();
    }

    public List<String> getEnemyTips() {
        return enemyTips.get();
    }

    @Searchable({int.class})
    public int getId() {
        if(coreData.getChampion().getId() == 0) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getId();
    }

    public Image getImage() {
        return image.get();
    }

    public Set<String> getIncludedData() {
        return includedData.get();
    }

    @Searchable(String.class)
    public String getKey() {
        if(coreData.getChampion().getKey() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getKey();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            CHAMPION_LOAD_GROUP,
            STATUS_LOAD_GROUP
        });
    }

    public String getLocale() {
        return coreData.getChampion().getLocale();
    }

    public String getLore() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getLore();
    }

    public int getMagicRating() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getMagicRating();
    }

    @Searchable(String.class)
    public String getName() {
        if(coreData.getChampion().getName() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getName();
    }

    public Passive getPassive() {
        return passive.get();
    }

    public int getPhysicalRating() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getPhysicalRating();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getChampion().getPlatform());
    }

    public SearchableList<RecommendedItems> getRecommendedItems() {
        return recommendedItems.get();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getChampion().getPlatform()).getRegion();
    }

    public String getResource() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getResource();
    }

    public SearchableList<Skin> getSkins() {
        return skins.get();
    }

    public SearchableList<ChampionSpell> getSpells() {
        return spells.get();
    }

    public ChampionStats getStats() {
        return stats.get();
    }

    public List<String> getTags() {
        return tags.get();
    }

    @Searchable(String.class)
    public String getTitle() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getTitle();
    }

    public String getVersion() {
        return coreData.getChampion().getVersion();
    }

    public boolean isEnabled() {
        if(coreData.getChampion().getId() == 0) {
            load(CHAMPION_LOAD_GROUP);
        }
        if(coreData.getStatus().getId() == 0) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabled();
    }

    public boolean isEnabledInCoopVsAI() {
        if(coreData.getChampion().getId() == 0) {
            load(CHAMPION_LOAD_GROUP);
        }
        if(coreData.getStatus().getId() == 0) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabledInCoopVsAI();
    }

    public boolean isEnabledInCustoms() {
        if(coreData.getChampion().getId() == 0) {
            load(CHAMPION_LOAD_GROUP);
        }
        if(coreData.getStatus().getId() == 0) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabledInCustoms();
    }

    public boolean isEnabledInRanked() {
        if(coreData.getChampion().getId() == 0) {
            load(CHAMPION_LOAD_GROUP);
        }
        if(coreData.getStatus().getId() == 0) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabledInRanked();
    }

    public boolean isFreeToPlay() {
        if(coreData.getChampion().getId() == 0) {
            load(CHAMPION_LOAD_GROUP);
        }
        if(coreData.getStatus().getId() == 0) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabled();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case CHAMPION_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getChampion().getId() != 0) {
                    builder.put("id", coreData.getChampion().getId());
                }
                if(coreData.getChampion().getName() != null) {
                    builder.put("name", coreData.getChampion().getName());
                }
                if(coreData.getChampion().getKey() != null) {
                    builder.put("key", coreData.getChampion().getKey());
                }
                if(coreData.getChampion().getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getChampion().getPlatform()));
                }
                if(coreData.getChampion().getVersion() != null) {
                    builder.put("version", coreData.getChampion().getVersion());
                }
                if(coreData.getChampion().getLocale() != null) {
                    builder.put("locale", coreData.getChampion().getLocale());
                }
                if(coreData.getChampion().getIncludedData() != null) {
                    builder.put("includedData", coreData.getChampion().getIncludedData());
                }

                coreData
                    .setChampion(Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Champion.class, builder.build()));
                break;
            case STATUS_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getChampion().getId() != 0) {
                    builder.put("id", coreData.getChampion().getId());
                }
                if(coreData.getChampion().getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getChampion().getPlatform()));
                }

                coreData.setStatus(Orianna.getSettings().getPipeline().get(ChampionStatus.class, builder.build()));
                if(coreData.getChampion().getId() == 0) {
                    coreData.getChampion().setId(coreData.getStatus().getId());
                }
                if(coreData.getChampion().getPlatform() == null) {
                    coreData.getChampion().setPlatform(coreData.getStatus().getPlatform());
                }
                break;
            default:
                break;
        }
    }
}
