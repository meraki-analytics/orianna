package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;
import java.util.Set;

import com.merakianalytics.orianna.types.data.CoreData;

public class Champion extends CoreData {
    private static final long serialVersionUID = 6905745329767401177L;
    private int difficultyRating, physicalRating, defenseRating, magicRating, id;
    private List<String> enemyTips, tags, allyTips;
    private Image image;
    private Set<String> includedData;
    private String name, title, resource, key, lore, blurb, locale, version, platform;
    private Passive passive;
    private List<RecommendedItems> recommendedItems;
    private List<Skin> skins;
    private List<ChampionSpell> spells;
    private ChampionStats stats;

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
        final Champion other = (Champion)obj;
        if(allyTips == null) {
            if(other.allyTips != null) {
                return false;
            }
        } else if(!allyTips.equals(other.allyTips)) {
            return false;
        }
        if(blurb == null) {
            if(other.blurb != null) {
                return false;
            }
        } else if(!blurb.equals(other.blurb)) {
            return false;
        }
        if(defenseRating != other.defenseRating) {
            return false;
        }
        if(difficultyRating != other.difficultyRating) {
            return false;
        }
        if(enemyTips == null) {
            if(other.enemyTips != null) {
                return false;
            }
        } else if(!enemyTips.equals(other.enemyTips)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        } else if(!image.equals(other.image)) {
            return false;
        }
        if(includedData == null) {
            if(other.includedData != null) {
                return false;
            }
        } else if(!includedData.equals(other.includedData)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        } else if(!key.equals(other.key)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
            return false;
        }
        if(lore == null) {
            if(other.lore != null) {
                return false;
            }
        } else if(!lore.equals(other.lore)) {
            return false;
        }
        if(magicRating != other.magicRating) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(passive == null) {
            if(other.passive != null) {
                return false;
            }
        } else if(!passive.equals(other.passive)) {
            return false;
        }
        if(physicalRating != other.physicalRating) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(recommendedItems == null) {
            if(other.recommendedItems != null) {
                return false;
            }
        } else if(!recommendedItems.equals(other.recommendedItems)) {
            return false;
        }
        if(resource == null) {
            if(other.resource != null) {
                return false;
            }
        } else if(!resource.equals(other.resource)) {
            return false;
        }
        if(skins == null) {
            if(other.skins != null) {
                return false;
            }
        } else if(!skins.equals(other.skins)) {
            return false;
        }
        if(spells == null) {
            if(other.spells != null) {
                return false;
            }
        } else if(!spells.equals(other.spells)) {
            return false;
        }
        if(stats == null) {
            if(other.stats != null) {
                return false;
            }
        } else if(!stats.equals(other.stats)) {
            return false;
        }
        if(tags == null) {
            if(other.tags != null) {
                return false;
            }
        } else if(!tags.equals(other.tags)) {
            return false;
        }
        if(title == null) {
            if(other.title != null) {
                return false;
            }
        } else if(!title.equals(other.title)) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    /**
     * @return the allyTips
     */
    public List<String> getAllyTips() {
        return allyTips;
    }

    /**
     * @return the blurb
     */
    public String getBlurb() {
        return blurb;
    }

    /**
     * @return the defenseRating
     */
    public int getDefenseRating() {
        return defenseRating;
    }

    /**
     * @return the difficultyRating
     */
    public int getDifficultyRating() {
        return difficultyRating;
    }

    /**
     * @return the enemyTips
     */
    public List<String> getEnemyTips() {
        return enemyTips;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the includedData
     */
    public Set<String> getIncludedData() {
        return includedData;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the lore
     */
    public String getLore() {
        return lore;
    }

    /**
     * @return the magicRating
     */
    public int getMagicRating() {
        return magicRating;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the passive
     */
    public Passive getPassive() {
        return passive;
    }

    /**
     * @return the physicalRating
     */
    public int getPhysicalRating() {
        return physicalRating;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the recommendedItems
     */
    public List<RecommendedItems> getRecommendedItems() {
        return recommendedItems;
    }

    /**
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * @return the skins
     */
    public List<Skin> getSkins() {
        return skins;
    }

    /**
     * @return the spells
     */
    public List<ChampionSpell> getSpells() {
        return spells;
    }

    /**
     * @return the stats
     */
    public ChampionStats getStats() {
        return stats;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (allyTips == null ? 0 : allyTips.hashCode());
        result = prime * result + (blurb == null ? 0 : blurb.hashCode());
        result = prime * result + defenseRating;
        result = prime * result + difficultyRating;
        result = prime * result + (enemyTips == null ? 0 : enemyTips.hashCode());
        result = prime * result + id;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (lore == null ? 0 : lore.hashCode());
        result = prime * result + magicRating;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (passive == null ? 0 : passive.hashCode());
        result = prime * result + physicalRating;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (recommendedItems == null ? 0 : recommendedItems.hashCode());
        result = prime * result + (resource == null ? 0 : resource.hashCode());
        result = prime * result + (skins == null ? 0 : skins.hashCode());
        result = prime * result + (spells == null ? 0 : spells.hashCode());
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param allyTips
     *        the allyTips to set
     */
    public void setAllyTips(final List<String> allyTips) {
        this.allyTips = allyTips;
    }

    /**
     * @param blurb
     *        the blurb to set
     */
    public void setBlurb(final String blurb) {
        this.blurb = blurb;
    }

    /**
     * @param defenseRating
     *        the defenseRating to set
     */
    public void setDefenseRating(final int defenseRating) {
        this.defenseRating = defenseRating;
    }

    /**
     * @param difficultyRating
     *        the difficultyRating to set
     */
    public void setDifficultyRating(final int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    /**
     * @param enemyTips
     *        the enemyTips to set
     */
    public void setEnemyTips(final List<String> enemyTips) {
        this.enemyTips = enemyTips;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param image
     *        the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param includedData
     *        the includedData to set
     */
    public void setIncludedData(final Set<String> includedData) {
        this.includedData = includedData;
    }

    /**
     * @param key
     *        the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param lore
     *        the lore to set
     */
    public void setLore(final String lore) {
        this.lore = lore;
    }

    /**
     * @param magicRating
     *        the magicRating to set
     */
    public void setMagicRating(final int magicRating) {
        this.magicRating = magicRating;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param passive
     *        the passive to set
     */
    public void setPassive(final Passive passive) {
        this.passive = passive;
    }

    /**
     * @param physicalRating
     *        the physicalRating to set
     */
    public void setPhysicalRating(final int physicalRating) {
        this.physicalRating = physicalRating;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param recommendedItems
     *        the recommendedItems to set
     */
    public void setRecommendedItems(final List<RecommendedItems> recommendedItems) {
        this.recommendedItems = recommendedItems;
    }

    /**
     * @param resource
     *        the resource to set
     */
    public void setResource(final String resource) {
        this.resource = resource;
    }

    /**
     * @param skins
     *        the skins to set
     */
    public void setSkins(final List<Skin> skins) {
        this.skins = skins;
    }

    /**
     * @param spells
     *        the spells to set
     */
    public void setSpells(final List<ChampionSpell> spells) {
        this.spells = spells;
    }

    /**
     * @param stats
     *        the stats to set
     */
    public void setStats(final ChampionStats stats) {
        this.stats = stats;
    }

    /**
     * @param tags
     *        the tags to set
     */
    public void setTags(final List<String> tags) {
        this.tags = tags;
    }

    /**
     * @param title
     *        the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
