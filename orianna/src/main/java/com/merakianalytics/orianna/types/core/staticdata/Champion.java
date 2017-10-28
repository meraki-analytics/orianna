package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
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
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;
import com.merakianalytics.orianna.types.data.CoreData;
import com.merakianalytics.orianna.types.data.champion.ChampionStatus;

public class Champion extends GhostObject<ChampionData> {
    public static class Builder {
        private Integer id;
        private Set<String> includedData;
        private String name, version, locale;
        private Platform platform;

        public Builder fromPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Champion get() {
            if(name == null && id == null) {
                throw new IllegalStateException("Must set an ID or name for the champion!");
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

            return Orianna.getSettings().getPipeline().get(Champion.class, builder.build());
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

    public static class ChampionData extends CoreData {
        private static final long serialVersionUID = 8024144237694671276L;
        private com.merakianalytics.orianna.types.data.staticdata.Champion champion;
        private ChampionStatus status;

        public ChampionData(final int id, final Platform platform, final String version, final String locale) {
            this(id, null, platform, version, locale);
        }

        public ChampionData(final int id, final String name, final Platform platform, final String version, final String locale) {
            champion = new com.merakianalytics.orianna.types.data.staticdata.Champion();
            champion.setId(id);
            champion.setName(name);
            champion.setPlatform(platform);
            champion.setVersion(version);
            champion.setLocale(locale);
            status = new ChampionStatus();
            status.setId(id);
            status.setPlatform(platform);
        }

        public ChampionData(final String name, final Platform platform, final String version, final String locale) {
            this(0, name, platform, version, locale);
        }

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
        return new Builder().named(name);
    }

    public static Builder withId(final int id) {
        return new Builder().withId(id);
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
            load(CHAMPION_LOAD_GROUP);
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

    private final Supplier<List<RecommendedItems>> recommendedItems = Suppliers.memoize(new Supplier<List<RecommendedItems>>() {
        @Override
        public List<RecommendedItems> get() {
            load(CHAMPION_LOAD_GROUP);
            final List<RecommendedItems> recommendedItems = new ArrayList<>(coreData.getChampion().getRecommendedItems().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.RecommendedItems items : coreData.getChampion().getRecommendedItems()) {
                recommendedItems.add(new RecommendedItems(items));
            }
            return Collections.unmodifiableList(recommendedItems);
        }
    });

    private final Supplier<List<Skin>> skins = Suppliers.memoize(new Supplier<List<Skin>>() {
        @Override
        public List<Skin> get() {
            load(CHAMPION_LOAD_GROUP);
            final List<Skin> skins = new ArrayList<>(coreData.getChampion().getSkins().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.Skin skin : coreData.getChampion().getSkins()) {
                skins.add(new Skin(skin));
            }
            return Collections.unmodifiableList(skins);
        }
    });

    private final Supplier<List<ChampionSpell>> spells = Suppliers.memoize(new Supplier<List<ChampionSpell>>() {
        @Override
        public List<ChampionSpell> get() {
            load(CHAMPION_LOAD_GROUP);
            final List<ChampionSpell> spells = new ArrayList<>(coreData.getChampion().getSpells().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.ChampionSpell spell : coreData.getChampion().getSpells()) {
                spells.add(new ChampionSpell(spell));
            }
            return Collections.unmodifiableList(spells);
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

    public Champion(final int id, final Platform platform, final String version, final String locale) {
        super(new ChampionData(id, platform, version, locale), 2);
    }

    public Champion(final String name, final Platform platform, final String version, final String locale) {
        super(new ChampionData(name, platform, version, locale), 2);
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

    public String getKey() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getKey();
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
        return coreData.getChampion().getPlatform();
    }

    public List<RecommendedItems> getRecommendedItems() {
        return recommendedItems.get();
    }

    public Region getRegion() {
        return coreData.getChampion().getPlatform().getRegion();
    }

    public String getResource() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getResource();
    }

    public List<Skin> getSkins() {
        return skins.get();
    }

    public List<ChampionSpell> getSpells() {
        return spells.get();
    }

    public ChampionStats getStats() {
        return stats.get();
    }

    public List<String> getTags() {
        return tags.get();
    }

    public String getTitle() {
        load(CHAMPION_LOAD_GROUP);
        return coreData.getChampion().getTitle();
    }

    public String getVersion() {
        return coreData.getChampion().getVersion();
    }

    public boolean isEnabled() {
        load(STATUS_LOAD_GROUP);
        return coreData.getStatus().isEnabled();
    }

    public boolean isEnabledInCoopVsAI() {
        load(STATUS_LOAD_GROUP);
        return coreData.getStatus().isEnabledInCoopVsAI();
    }

    public boolean isEnabledInCustoms() {
        load(STATUS_LOAD_GROUP);
        return coreData.getStatus().isEnabledInCustoms();
    }

    public boolean isEnabledInRanked() {
        load(STATUS_LOAD_GROUP);
        return coreData.getStatus().isEnabledInRanked();
    }

    public boolean isFreeToPlay() {
        load(STATUS_LOAD_GROUP);
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
                if(coreData.getChampion().getPlatform() != null) {
                    builder.put("platform", coreData.getChampion().getPlatform());
                }
                if(coreData.getChampion().getVersion() != null) {
                    builder.put("version", coreData.getChampion().getVersion());
                }
                if(coreData.getChampion().getLocale() != null) {
                    builder.put("locale", coreData.getChampion().getLocale());
                }

                coreData.setChampion(
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Champion.class, builder.build()));
                break;
            case STATUS_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getChampion().getId() != 0) {
                    builder.put("id", coreData.getChampion().getId());
                }
                if(coreData.getChampion().getName() != null) {
                    builder.put("name", coreData.getChampion().getName());
                }
                if(coreData.getChampion().getPlatform() != null) {
                    builder.put("platform", coreData.getChampion().getPlatform());
                }

                coreData.setStatus(
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.champion.ChampionStatus.class, builder.build()));
                break;
            default:
                break;
        }
    }
}
