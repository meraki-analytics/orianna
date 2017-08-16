package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;
import com.merakianalytics.orianna.types.data.CoreData;
import com.merakianalytics.orianna.types.data.champion.ChampionStatus;

public class Champion extends GhostObject<ChampionData> {
    public static class ChampionData extends CoreData {
        private static final long serialVersionUID = -7102360131668168080L;
        private com.merakianalytics.orianna.types.data.staticdata.Champion champion;
        private ChampionStatus status;

        public ChampionData() {
            this(null, null);
        }

        public ChampionData(final ChampionStatus status) {
            this(null, status);
        }

        public ChampionData(final com.merakianalytics.orianna.types.data.staticdata.Champion champion) {
            this(champion, null);
        }

        public ChampionData(final com.merakianalytics.orianna.types.data.staticdata.Champion champion, final ChampionStatus status) {
            this.champion = champion;
            this.status = status;
        }

        @Override
        public boolean equals(final Object obj) {
            if(this == obj) {
                return true;
            }
            if(obj == null) {
                return false;
            }
            if(getClass() != obj.getClass()) {
                return false;
            }
            final ChampionData other = (ChampionData)obj;
            if(champion == null) {
                if(other.champion != null) {
                    return false;
                }
            } else if(!champion.equals(other.champion)) {
                return false;
            }
            if(status == null) {
                if(other.status != null) {
                    return false;
                }
            } else if(!status.equals(other.status)) {
                return false;
            }
            return true;
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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (champion == null ? 0 : champion.hashCode());
            result = prime * result + (status == null ? 0 : status.hashCode());
            return result;
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

    private static final String CHAMPION_LOAD_GROUP = "champion";
    private static final long serialVersionUID = 8187636141258147929L;
    private static final String STATUS_LOAD_GROUP = "status";

    public static Champion withId(final int id) {
        return withId(id, Orianna.getSettings().getDefaultPlatform(), Orianna.getSettings().getCurrentVersion(),
            Orianna.getSettings().getDefaultPlatform().getDefaultLocale());
    }

    public static Champion withId(final int id, final Platform platform) {
        return withId(id, platform, Orianna.getSettings().getCurrentVersion(), platform.getDefaultLocale());
    }

    public static Champion withId(final int id, final Platform platform, final String version) {
        return withId(id, platform, version, platform.getDefaultLocale());
    }

    public static Champion withId(final int id, final Platform platform, final String version, final String locale) {
        return Orianna.getSettings().getPipeline().get(Champion.class,
            ImmutableMap.<String, Object> of("id", id, "platform", platform, "version", version, "locale", locale));
    }

    private final Supplier<List<String>> allyTips = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
            return Collections.unmodifiableList(coreData.getChampion().getAllyTips());
        }
    });

    private final Supplier<List<String>> enemyTips = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
            return Collections.unmodifiableList(coreData.getChampion().getEnemyTips());
        }
    });

    private Integer id;

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
            return new Image(coreData.getChampion().getImage());
        }
    });

    private final Supplier<Set<String>> includedData = Suppliers.memoize(new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
            return Collections.unmodifiableSet(coreData.getChampion().getIncludedData());
        }
    });

    private String locale;
    private String name;

    private final Supplier<Passive> passive = Suppliers.memoize(new Supplier<Passive>() {
        @Override
        public Passive get() {
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
            return new Passive(coreData.getChampion().getPassive());
        }
    });

    private Platform platform;

    private final Supplier<List<RecommendedItems>> recommendedItems = Suppliers.memoize(new Supplier<List<RecommendedItems>>() {
        @Override
        public List<RecommendedItems> get() {
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
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
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
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
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
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
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
            return new ChampionStats(coreData.getChampion().getStats());
        }
    });

    private final Supplier<List<String>> tags = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            if(coreData.getChampion() == null) {
                load(CHAMPION_LOAD_GROUP);
            }
            return Collections.unmodifiableList(coreData.getChampion().getTags());
        }
    });

    private String version;

    public Champion(final ChampionStatus status) {
        super(new ChampionData(status));
        id = status.getId();
        platform = status.getPlatform();
        version = Orianna.getSettings().getCurrentVersion();
        locale = status.getPlatform().getDefaultLocale();
    }

    public Champion(final com.merakianalytics.orianna.types.data.staticdata.Champion champion) {
        super(new ChampionData(champion));
        id = champion.getId();
        name = champion.getName();
        platform = champion.getPlatform();
        version = champion.getVersion();
        locale = champion.getLocale();
    }

    public Champion(final com.merakianalytics.orianna.types.data.staticdata.Champion champion, final ChampionStatus status) {
        super(new ChampionData(champion, status));
        id = champion.getId();
        name = champion.getName();
        platform = champion.getPlatform();
        version = champion.getVersion();
        locale = champion.getLocale();
    }

    public Champion(final Integer id, final String name, final Platform platform, final String version, final String locale) {
        super(new ChampionData());
        this.id = id;
        this.name = name;
        this.platform = platform;
        this.version = version;
        this.locale = locale;
    }

    public List<String> getAllyTips() {
        return allyTips.get();
    }

    public String getBlurb() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getBlurb();
    }

    public int getDefenseRating() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getDefenseRating();
    }

    public int getDifficultyRating() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getDifficultyRating();
    }

    public List<String> getEnemyTips() {
        return enemyTips.get();
    }

    public int getId() {
        if(id == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return id;
    }

    public Image getImage() {
        return image.get();
    }

    public Set<String> getIncludedData() {
        return includedData.get();
    }

    public String getKey() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getKey();
    }

    public String getLocale() {
        return locale;
    }

    public String getLore() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getLore();
    }

    public int getMagicRating() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getMagicRating();
    }

    public String getName() {
        if(name == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return name;
    }

    public Passive getPassive() {
        return passive.get();
    }

    public int getPhysicalRating() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getPhysicalRating();
    }

    public Platform getPlatform() {
        return platform;
    }

    public List<RecommendedItems> getRecommendedItems() {
        return recommendedItems.get();
    }

    public Region getRegion() {
        return platform.getRegion();
    }

    public String getResource() {
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
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
        if(coreData.getChampion() == null) {
            load(CHAMPION_LOAD_GROUP);
        }
        return coreData.getChampion().getTitle();
    }

    public String getVersion() {
        return version;
    }

    public boolean isEnabled() {
        if(coreData.getStatus() == null) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabled();
    }

    public boolean isEnabledInCoopVsAI() {
        if(coreData.getStatus() == null) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabledInCoopVsAI();
    }

    public boolean isEnabledInCustoms() {
        if(coreData.getStatus() == null) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabledInCustoms();
    }

    public boolean isEnabledInRanked() {
        if(coreData.getStatus() == null) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabledInRanked();
    }

    public boolean isFreeToPlay() {
        if(coreData.getStatus() == null) {
            load(STATUS_LOAD_GROUP);
        }
        return coreData.getStatus().isEnabled();
    }

    @Override
    protected void loadCoreData(final String group) {
        switch(group) {
            case CHAMPION_LOAD_GROUP:
                coreData.setChampion(Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Champion.class,
                    ImmutableMap.<String, Object> of(
                        "id", id,
                        "platform", platform,
                        "version", version,
                        "locale", locale)));
                id = coreData.getChampion().getId();
                name = coreData.getChampion().getName();
                platform = coreData.getChampion().getPlatform();
                version = coreData.getChampion().getVersion();
                locale = coreData.getChampion().getLocale();
                break;
            case STATUS_LOAD_GROUP:
                coreData.setStatus(Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.champion.ChampionStatus.class,
                    ImmutableMap.<String, Object> of(
                        "id", id,
                        "platform", platform)));
                id = coreData.getStatus().getId();
                platform = coreData.getStatus().getPlatform();
                break;
            default:
                break;
        }
    }
}
