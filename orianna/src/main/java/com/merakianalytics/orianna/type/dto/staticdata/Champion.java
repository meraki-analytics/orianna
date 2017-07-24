package com.merakianalytics.orianna.type.dto.staticdata;

import java.util.List;
import java.util.Set;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Champion extends DataObject {
    private static final long serialVersionUID = -3060897308422661168L;
    private List<String> enemytips, tags, allytips;
    private int id;
    private Image image;
    private Set<String> includedData;
    private Info info;
    private String name, title, partype, key, lore, blurb, platform, locale, version;
    private Passive passive;
    private List<Recommended> recommended;
    private List<Skin> skins;
    private List<ChampionSpell> spells;
    private Stats stats;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        if(allytips == null) {
            if(other.allytips != null) {
                return false;
            }
        } else if(!allytips.equals(other.allytips)) {
            return false;
        }
        if(blurb == null) {
            if(other.blurb != null) {
                return false;
            }
        } else if(!blurb.equals(other.blurb)) {
            return false;
        }
        if(enemytips == null) {
            if(other.enemytips != null) {
                return false;
            }
        } else if(!enemytips.equals(other.enemytips)) {
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
        if(info == null) {
            if(other.info != null) {
                return false;
            }
        } else if(!info.equals(other.info)) {
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
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(partype == null) {
            if(other.partype != null) {
                return false;
            }
        } else if(!partype.equals(other.partype)) {
            return false;
        }
        if(passive == null) {
            if(other.passive != null) {
                return false;
            }
        } else if(!passive.equals(other.passive)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(recommended == null) {
            if(other.recommended != null) {
                return false;
            }
        } else if(!recommended.equals(other.recommended)) {
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
     * @return the allytips
     */
    public List<String> getAllytips() {
        return allytips;
    }

    /**
     * @return the blurb
     */
    public String getBlurb() {
        return blurb;
    }

    /**
     * @return the enemytips
     */
    public List<String> getEnemytips() {
        return enemytips;
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
     * @return the info
     */
    public Info getInfo() {
        return info;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the partype
     */
    public String getPartype() {
        return partype;
    }

    /**
     * @return the passive
     */
    public Passive getPassive() {
        return passive;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the recommended
     */
    public List<Recommended> getRecommended() {
        return recommended;
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
    public Stats getStats() {
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (allytips == null ? 0 : allytips.hashCode());
        result = prime * result + (blurb == null ? 0 : blurb.hashCode());
        result = prime * result + (enemytips == null ? 0 : enemytips.hashCode());
        result = prime * result + id;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (info == null ? 0 : info.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (lore == null ? 0 : lore.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (partype == null ? 0 : partype.hashCode());
        result = prime * result + (passive == null ? 0 : passive.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (recommended == null ? 0 : recommended.hashCode());
        result = prime * result + (skins == null ? 0 : skins.hashCode());
        result = prime * result + (spells == null ? 0 : spells.hashCode());
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param allytips
     *        the allytips to set
     */
    public void setAllytips(final List<String> allytips) {
        this.allytips = allytips;
    }

    /**
     * @param blurb
     *        the blurb to set
     */
    public void setBlurb(final String blurb) {
        this.blurb = blurb;
    }

    /**
     * @param enemytips
     *        the enemytips to set
     */
    public void setEnemytips(final List<String> enemytips) {
        this.enemytips = enemytips;
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
     * @param info
     *        the info to set
     */
    public void setInfo(final Info info) {
        this.info = info;
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
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param partype
     *        the partype to set
     */
    public void setPartype(final String partype) {
        this.partype = partype;
    }

    /**
     * @param passive
     *        the passive to set
     */
    public void setPassive(final Passive passive) {
        this.passive = passive;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param recommended
     *        the recommended to set
     */
    public void setRecommended(final List<Recommended> recommended) {
        this.recommended = recommended;
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
    public void setStats(final Stats stats) {
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
