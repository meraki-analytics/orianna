package com.merakianalytics.orianna.type.dto.staticdata;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Item extends DataObject {
    private static final long serialVersionUID = 2549241438645633740L;
    private Map<String, String> effect;
    private Gold gold;
    private boolean hideFromAll, inStore, consumeOnFull, consumed;
    private int id, specialRecipe, depth, stacks;
    private Image image;
    private Set<String> includedData;
    private List<String> into, tags, from;
    private Map<String, Boolean> maps;
    private String plaintext, colloq, description, requiredChampion, group, name, sanitizedDescription, platform, version, locale;
    private InventoryDataStats stats;

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
        final Item other = (Item)obj;
        if(colloq == null) {
            if(other.colloq != null) {
                return false;
            }
        } else if(!colloq.equals(other.colloq)) {
            return false;
        }
        if(consumeOnFull != other.consumeOnFull) {
            return false;
        }
        if(consumed != other.consumed) {
            return false;
        }
        if(depth != other.depth) {
            return false;
        }
        if(description == null) {
            if(other.description != null) {
                return false;
            }
        } else if(!description.equals(other.description)) {
            return false;
        }
        if(effect == null) {
            if(other.effect != null) {
                return false;
            }
        } else if(!effect.equals(other.effect)) {
            return false;
        }
        if(from == null) {
            if(other.from != null) {
                return false;
            }
        } else if(!from.equals(other.from)) {
            return false;
        }
        if(gold == null) {
            if(other.gold != null) {
                return false;
            }
        } else if(!gold.equals(other.gold)) {
            return false;
        }
        if(group == null) {
            if(other.group != null) {
                return false;
            }
        } else if(!group.equals(other.group)) {
            return false;
        }
        if(hideFromAll != other.hideFromAll) {
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
        if(inStore != other.inStore) {
            return false;
        }
        if(includedData == null) {
            if(other.includedData != null) {
                return false;
            }
        } else if(!includedData.equals(other.includedData)) {
            return false;
        }
        if(into == null) {
            if(other.into != null) {
                return false;
            }
        } else if(!into.equals(other.into)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
            return false;
        }
        if(maps == null) {
            if(other.maps != null) {
                return false;
            }
        } else if(!maps.equals(other.maps)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(plaintext == null) {
            if(other.plaintext != null) {
                return false;
            }
        } else if(!plaintext.equals(other.plaintext)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(requiredChampion == null) {
            if(other.requiredChampion != null) {
                return false;
            }
        } else if(!requiredChampion.equals(other.requiredChampion)) {
            return false;
        }
        if(sanitizedDescription == null) {
            if(other.sanitizedDescription != null) {
                return false;
            }
        } else if(!sanitizedDescription.equals(other.sanitizedDescription)) {
            return false;
        }
        if(specialRecipe != other.specialRecipe) {
            return false;
        }
        if(stacks != other.stacks) {
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
     * @return the colloq
     */
    public String getColloq() {
        return colloq;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the effect
     */
    public Map<String, String> getEffect() {
        return effect;
    }

    /**
     * @return the from
     */
    public List<String> getFrom() {
        return from;
    }

    /**
     * @return the gold
     */
    public Gold getGold() {
        return gold;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
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
     * @return the into
     */
    public List<String> getInto() {
        return into;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the maps
     */
    public Map<String, Boolean> getMaps() {
        return maps;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the plaintext
     */
    public String getPlaintext() {
        return plaintext;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the requiredChampion
     */
    public String getRequiredChampion() {
        return requiredChampion;
    }

    /**
     * @return the sanitizedDescription
     */
    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    /**
     * @return the specialRecipe
     */
    public int getSpecialRecipe() {
        return specialRecipe;
    }

    /**
     * @return the stacks
     */
    public int getStacks() {
        return stacks;
    }

    /**
     * @return the stats
     */
    public InventoryDataStats getStats() {
        return stats;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
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
        result = prime * result + (colloq == null ? 0 : colloq.hashCode());
        result = prime * result + (consumeOnFull ? 1231 : 1237);
        result = prime * result + (consumed ? 1231 : 1237);
        result = prime * result + depth;
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (effect == null ? 0 : effect.hashCode());
        result = prime * result + (from == null ? 0 : from.hashCode());
        result = prime * result + (gold == null ? 0 : gold.hashCode());
        result = prime * result + (group == null ? 0 : group.hashCode());
        result = prime * result + (hideFromAll ? 1231 : 1237);
        result = prime * result + id;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (inStore ? 1231 : 1237);
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (into == null ? 0 : into.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (maps == null ? 0 : maps.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (plaintext == null ? 0 : plaintext.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (requiredChampion == null ? 0 : requiredChampion.hashCode());
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        result = prime * result + specialRecipe;
        result = prime * result + stacks;
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @return the consumed
     */
    public boolean isConsumed() {
        return consumed;
    }

    /**
     * @return the consumeOnFull
     */
    public boolean isConsumeOnFull() {
        return consumeOnFull;
    }

    /**
     * @return the hideFromAll
     */
    public boolean isHideFromAll() {
        return hideFromAll;
    }

    /**
     * @return the inStore
     */
    public boolean isInStore() {
        return inStore;
    }

    /**
     * @param colloq
     *        the colloq to set
     */
    public void setColloq(final String colloq) {
        this.colloq = colloq;
    }

    /**
     * @param consumed
     *        the consumed to set
     */
    public void setConsumed(final boolean consumed) {
        this.consumed = consumed;
    }

    /**
     * @param consumeOnFull
     *        the consumeOnFull to set
     */
    public void setConsumeOnFull(final boolean consumeOnFull) {
        this.consumeOnFull = consumeOnFull;
    }

    /**
     * @param depth
     *        the depth to set
     */
    public void setDepth(final int depth) {
        this.depth = depth;
    }

    /**
     * @param description
     *        the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param effect
     *        the effect to set
     */
    public void setEffect(final Map<String, String> effect) {
        this.effect = effect;
    }

    /**
     * @param from
     *        the from to set
     */
    public void setFrom(final List<String> from) {
        this.from = from;
    }

    /**
     * @param gold
     *        the gold to set
     */
    public void setGold(final Gold gold) {
        this.gold = gold;
    }

    /**
     * @param group
     *        the group to set
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * @param hideFromAll
     *        the hideFromAll to set
     */
    public void setHideFromAll(final boolean hideFromAll) {
        this.hideFromAll = hideFromAll;
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
     * @param inStore
     *        the inStore to set
     */
    public void setInStore(final boolean inStore) {
        this.inStore = inStore;
    }

    /**
     * @param into
     *        the into to set
     */
    public void setInto(final List<String> into) {
        this.into = into;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param maps
     *        the maps to set
     */
    public void setMaps(final Map<String, Boolean> maps) {
        this.maps = maps;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param plaintext
     *        the plaintext to set
     */
    public void setPlaintext(final String plaintext) {
        this.plaintext = plaintext;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param requiredChampion
     *        the requiredChampion to set
     */
    public void setRequiredChampion(final String requiredChampion) {
        this.requiredChampion = requiredChampion;
    }

    /**
     * @param sanitizedDescription
     *        the sanitizedDescription to set
     */
    public void setSanitizedDescription(final String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * @param specialRecipe
     *        the specialRecipe to set
     */
    public void setSpecialRecipe(final int specialRecipe) {
        this.specialRecipe = specialRecipe;
    }

    /**
     * @param stacks
     *        the stacks to set
     */
    public void setStacks(final int stacks) {
        this.stacks = stacks;
    }

    /**
     * @param stats
     *        the stats to set
     */
    public void setStats(final InventoryDataStats stats) {
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
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
