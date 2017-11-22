package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.orianna.types.data.CoreData;

public class Item extends CoreData {
    private static final long serialVersionUID = -8963521454120805493L;
    private List<Integer> buildsInto, buildsFrom;
    private Map<String, String> effects;
    private Image image;
    private Set<String> includedData, keywords;
    private Set<Integer> maps;
    private String plaintext, description, requiredChampionKey, group, name, sanitizedDescription, version, locale, platform;
    private boolean purchasable, hiddenFromAll, inStore, consumedWhenFull, consumed;
    private int sellPrice, basePrice, totalPrice, id, source, tier, maxStacks;
    private ItemStats stats;
    private List<String> tags;

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
        if(basePrice != other.basePrice) {
            return false;
        }
        if(buildsFrom == null) {
            if(other.buildsFrom != null) {
                return false;
            }
        } else if(!buildsFrom.equals(other.buildsFrom)) {
            return false;
        }
        if(buildsInto == null) {
            if(other.buildsInto != null) {
                return false;
            }
        } else if(!buildsInto.equals(other.buildsInto)) {
            return false;
        }
        if(consumed != other.consumed) {
            return false;
        }
        if(consumedWhenFull != other.consumedWhenFull) {
            return false;
        }
        if(description == null) {
            if(other.description != null) {
                return false;
            }
        } else if(!description.equals(other.description)) {
            return false;
        }
        if(effects == null) {
            if(other.effects != null) {
                return false;
            }
        } else if(!effects.equals(other.effects)) {
            return false;
        }
        if(group == null) {
            if(other.group != null) {
                return false;
            }
        } else if(!group.equals(other.group)) {
            return false;
        }
        if(hiddenFromAll != other.hiddenFromAll) {
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
        if(keywords == null) {
            if(other.keywords != null) {
                return false;
            }
        } else if(!keywords.equals(other.keywords)) {
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
        if(maxStacks != other.maxStacks) {
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
        if(purchasable != other.purchasable) {
            return false;
        }
        if(requiredChampionKey == null) {
            if(other.requiredChampionKey != null) {
                return false;
            }
        } else if(!requiredChampionKey.equals(other.requiredChampionKey)) {
            return false;
        }
        if(sanitizedDescription == null) {
            if(other.sanitizedDescription != null) {
                return false;
            }
        } else if(!sanitizedDescription.equals(other.sanitizedDescription)) {
            return false;
        }
        if(sellPrice != other.sellPrice) {
            return false;
        }
        if(source != other.source) {
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
        if(tier != other.tier) {
            return false;
        }
        if(totalPrice != other.totalPrice) {
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
     * @return the basePrice
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * @return the buildsFrom
     */
    public List<Integer> getBuildsFrom() {
        return buildsFrom;
    }

    /**
     * @return the buildsInto
     */
    public List<Integer> getBuildsInto() {
        return buildsInto;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the effects
     */
    public Map<String, String> getEffects() {
        return effects;
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
     * @return the keywords
     */
    public Set<String> getKeywords() {
        return keywords;
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
    public Set<Integer> getMaps() {
        return maps;
    }

    /**
     * @return the maxStacks
     */
    public int getMaxStacks() {
        return maxStacks;
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
     * @return the requiredChampionKey
     */
    public String getRequiredChampionKey() {
        return requiredChampionKey;
    }

    /**
     * @return the sanitizedDescription
     */
    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    /**
     * @return the sellPrice
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * @return the source
     */
    public int getSource() {
        return source;
    }

    /**
     * @return the stats
     */
    public ItemStats getStats() {
        return stats;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @return the tier
     */
    public int getTier() {
        return tier;
    }

    /**
     * @return the totalPrice
     */
    public int getTotalPrice() {
        return totalPrice;
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
        result = prime * result + basePrice;
        result = prime * result + (buildsFrom == null ? 0 : buildsFrom.hashCode());
        result = prime * result + (buildsInto == null ? 0 : buildsInto.hashCode());
        result = prime * result + (consumed ? 1231 : 1237);
        result = prime * result + (consumedWhenFull ? 1231 : 1237);
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (effects == null ? 0 : effects.hashCode());
        result = prime * result + (group == null ? 0 : group.hashCode());
        result = prime * result + (hiddenFromAll ? 1231 : 1237);
        result = prime * result + id;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (inStore ? 1231 : 1237);
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (keywords == null ? 0 : keywords.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (maps == null ? 0 : maps.hashCode());
        result = prime * result + maxStacks;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (plaintext == null ? 0 : plaintext.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (purchasable ? 1231 : 1237);
        result = prime * result + (requiredChampionKey == null ? 0 : requiredChampionKey.hashCode());
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        result = prime * result + sellPrice;
        result = prime * result + source;
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        result = prime * result + tier;
        result = prime * result + totalPrice;
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
     * @return the consumedWhenFull
     */
    public boolean isConsumedWhenFull() {
        return consumedWhenFull;
    }

    /**
     * @return the hiddenFromAll
     */
    public boolean isHiddenFromAll() {
        return hiddenFromAll;
    }

    /**
     * @return the inStore
     */
    public boolean isInStore() {
        return inStore;
    }

    /**
     * @return the purchasable
     */
    public boolean isPurchasable() {
        return purchasable;
    }

    /**
     * @param basePrice
     *        the basePrice to set
     */
    public void setBasePrice(final int basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @param buildsFrom
     *        the buildsFrom to set
     */
    public void setBuildsFrom(final List<Integer> buildsFrom) {
        this.buildsFrom = buildsFrom;
    }

    /**
     * @param buildsInto
     *        the buildsInto to set
     */
    public void setBuildsInto(final List<Integer> buildsInto) {
        this.buildsInto = buildsInto;
    }

    /**
     * @param consumed
     *        the consumed to set
     */
    public void setConsumed(final boolean consumed) {
        this.consumed = consumed;
    }

    /**
     * @param consumedWhenFull
     *        the consumedWhenFull to set
     */
    public void setConsumedWhenFull(final boolean consumedWhenFull) {
        this.consumedWhenFull = consumedWhenFull;
    }

    /**
     * @param description
     *        the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param effects
     *        the effects to set
     */
    public void setEffects(final Map<String, String> effects) {
        this.effects = effects;
    }

    /**
     * @param group
     *        the group to set
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * @param hiddenFromAll
     *        the hiddenFromAll to set
     */
    public void setHiddenFromAll(final boolean hiddenFromAll) {
        this.hiddenFromAll = hiddenFromAll;
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
     * @param keywords
     *        the keywords to set
     */
    public void setKeywords(final Set<String> keywords) {
        this.keywords = keywords;
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
    public void setMaps(final Set<Integer> maps) {
        this.maps = maps;
    }

    /**
     * @param maxStacks
     *        the maxStacks to set
     */
    public void setMaxStacks(final int maxStacks) {
        this.maxStacks = maxStacks;
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
     * @param purchasable
     *        the purchasable to set
     */
    public void setPurchasable(final boolean purchasable) {
        this.purchasable = purchasable;
    }

    /**
     * @param requiredChampionKey
     *        the requiredChampionKey to set
     */
    public void setRequiredChampionKey(final String requiredChampionKey) {
        this.requiredChampionKey = requiredChampionKey;
    }

    /**
     * @param sanitizedDescription
     *        the sanitizedDescription to set
     */
    public void setSanitizedDescription(final String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * @param sellPrice
     *        the sellPrice to set
     */
    public void setSellPrice(final int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * @param source
     *        the source to set
     */
    public void setSource(final int source) {
        this.source = source;
    }

    /**
     * @param stats
     *        the stats to set
     */
    public void setStats(final ItemStats stats) {
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
     * @param tier
     *        the tier to set
     */
    public void setTier(final int tier) {
        this.tier = tier;
    }

    /**
     * @param totalPrice
     *        the totalPrice to set
     */
    public void setTotalPrice(final int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
