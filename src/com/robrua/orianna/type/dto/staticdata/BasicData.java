package com.robrua.orianna.type.dto.staticdata;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "basicdata")
public class BasicData extends OriannaDto {
    private static final long serialVersionUID = 3141016508416237683L;
    private String colloq, name, requiredChampion;
    private Boolean consumeOnFull, consumed, hideFromAll, inStore;
    private Integer depth, specialRecipe, stacks;

    @Lob
    private String description, plaintext, sanitizedDescription;

    @ElementCollection
    @CollectionTable(name = "basicdata_from", joinColumns = @JoinColumn(name = "basicdata_id") )
    @Column(name = "frm")
    private List<String> from;

    @OneToOne(cascade = CascadeType.ALL)
    private Gold gold;

    @Column(name = "grp")
    private String group;

    @Id
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @ElementCollection
    @CollectionTable(name = "basicdata_into", joinColumns = @JoinColumn(name = "basicdata_id") )
    @Column(name = "nto")
    private List<String> into;

    @ElementCollection
    @CollectionTable(name = "basicdata_map", joinColumns = @JoinColumn(name = "basicdata_id") )
    private Map<String, Boolean> maps;

    @OneToOne(cascade = CascadeType.ALL)
    private MetaData rune;

    @OneToOne(cascade = CascadeType.ALL)
    private BasicDataStats stats;

    @ElementCollection
    @CollectionTable(name = "basicdata_tag", joinColumns = @JoinColumn(name = "basicdata_id") )
    private List<String> tags;

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
        if(!(obj instanceof BasicData)) {
            return false;
        }
        final BasicData other = (BasicData)obj;
        if(colloq == null) {
            if(other.colloq != null) {
                return false;
            }
        }
        else if(!colloq.equals(other.colloq)) {
            return false;
        }
        if(consumeOnFull == null) {
            if(other.consumeOnFull != null) {
                return false;
            }
        }
        else if(!consumeOnFull.equals(other.consumeOnFull)) {
            return false;
        }
        if(consumed == null) {
            if(other.consumed != null) {
                return false;
            }
        }
        else if(!consumed.equals(other.consumed)) {
            return false;
        }
        if(depth == null) {
            if(other.depth != null) {
                return false;
            }
        }
        else if(!depth.equals(other.depth)) {
            return false;
        }
        if(description == null) {
            if(other.description != null) {
                return false;
            }
        }
        else if(!description.equals(other.description)) {
            return false;
        }
        if(from == null) {
            if(other.from != null) {
                return false;
            }
        }
        else if(!from.equals(other.from)) {
            return false;
        }
        if(gold == null) {
            if(other.gold != null) {
                return false;
            }
        }
        else if(!gold.equals(other.gold)) {
            return false;
        }
        if(group == null) {
            if(other.group != null) {
                return false;
            }
        }
        else if(!group.equals(other.group)) {
            return false;
        }
        if(hideFromAll == null) {
            if(other.hideFromAll != null) {
                return false;
            }
        }
        else if(!hideFromAll.equals(other.hideFromAll)) {
            return false;
        }
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
            return false;
        }
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        }
        else if(!image.equals(other.image)) {
            return false;
        }
        if(inStore == null) {
            if(other.inStore != null) {
                return false;
            }
        }
        else if(!inStore.equals(other.inStore)) {
            return false;
        }
        if(into == null) {
            if(other.into != null) {
                return false;
            }
        }
        else if(!into.equals(other.into)) {
            return false;
        }
        if(maps == null) {
            if(other.maps != null) {
                return false;
            }
        }
        else if(!maps.equals(other.maps)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(plaintext == null) {
            if(other.plaintext != null) {
                return false;
            }
        }
        else if(!plaintext.equals(other.plaintext)) {
            return false;
        }
        if(requiredChampion == null) {
            if(other.requiredChampion != null) {
                return false;
            }
        }
        else if(!requiredChampion.equals(other.requiredChampion)) {
            return false;
        }
        if(rune == null) {
            if(other.rune != null) {
                return false;
            }
        }
        else if(!rune.equals(other.rune)) {
            return false;
        }
        if(sanitizedDescription == null) {
            if(other.sanitizedDescription != null) {
                return false;
            }
        }
        else if(!sanitizedDescription.equals(other.sanitizedDescription)) {
            return false;
        }
        if(specialRecipe == null) {
            if(other.specialRecipe != null) {
                return false;
            }
        }
        else if(!specialRecipe.equals(other.specialRecipe)) {
            return false;
        }
        if(stacks == null) {
            if(other.stacks != null) {
                return false;
            }
        }
        else if(!stacks.equals(other.stacks)) {
            return false;
        }
        if(stats == null) {
            if(other.stats != null) {
                return false;
            }
        }
        else if(!stats.equals(other.stats)) {
            return false;
        }
        if(tags == null) {
            if(other.tags != null) {
                return false;
            }
        }
        else if(!tags.equals(other.tags)) {
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
     * @return the consumed
     */
    public Boolean getConsumed() {
        return consumed;
    }

    /**
     * @return the consumeOnFull
     */
    public Boolean getConsumeOnFull() {
        return consumeOnFull;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Integer.class)) {
            return "id";
        }
        return null;
    }

    /**
     * @return the depth
     */
    public Integer getDepth() {
        return depth;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * @return the hideFromAll
     */
    public Boolean getHideFromAll() {
        return hideFromAll;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the inStore
     */
    public Boolean getInStore() {
        return inStore;
    }

    /**
     * @return the into
     */
    public List<String> getInto() {
        return into;
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
     * @return the requiredChampion
     */
    public String getRequiredChampion() {
        return requiredChampion;
    }

    /**
     * @return the rune
     */
    public MetaData getRune() {
        return rune;
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
    public Integer getSpecialRecipe() {
        return specialRecipe;
    }

    /**
     * @return the stacks
     */
    public Integer getStacks() {
        return stacks;
    }

    /**
     * @return the stats
     */
    public BasicDataStats getStats() {
        return stats;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
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
        result = prime * result + (consumeOnFull == null ? 0 : consumeOnFull.hashCode());
        result = prime * result + (consumed == null ? 0 : consumed.hashCode());
        result = prime * result + (depth == null ? 0 : depth.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (from == null ? 0 : from.hashCode());
        result = prime * result + (gold == null ? 0 : gold.hashCode());
        result = prime * result + (group == null ? 0 : group.hashCode());
        result = prime * result + (hideFromAll == null ? 0 : hideFromAll.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (inStore == null ? 0 : inStore.hashCode());
        result = prime * result + (into == null ? 0 : into.hashCode());
        result = prime * result + (maps == null ? 0 : maps.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (plaintext == null ? 0 : plaintext.hashCode());
        result = prime * result + (requiredChampion == null ? 0 : requiredChampion.hashCode());
        result = prime * result + (rune == null ? 0 : rune.hashCode());
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        result = prime * result + (specialRecipe == null ? 0 : specialRecipe.hashCode());
        result = prime * result + (stacks == null ? 0 : stacks.hashCode());
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        return result;
    }

    /**
     * @param colloq
     *            the colloq to set
     */
    public void setColloq(final String colloq) {
        this.colloq = colloq;
    }

    /**
     * @param consumed
     *            the consumed to set
     */
    public void setConsumed(final Boolean consumed) {
        this.consumed = consumed;
    }

    /**
     * @param consumeOnFull
     *            the consumeOnFull to set
     */
    public void setConsumeOnFull(final Boolean consumeOnFull) {
        this.consumeOnFull = consumeOnFull;
    }

    /**
     * @param depth
     *            the depth to set
     */
    public void setDepth(final Integer depth) {
        this.depth = depth;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param from
     *            the from to set
     */
    public void setFrom(final List<String> from) {
        this.from = from;
    }

    /**
     * @param gold
     *            the gold to set
     */
    public void setGold(final Gold gold) {
        this.gold = gold;
    }

    /**
     * @param group
     *            the group to set
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * @param hideFromAll
     *            the hideFromAll to set
     */
    public void setHideFromAll(final Boolean hideFromAll) {
        this.hideFromAll = hideFromAll;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param inStore
     *            the inStore to set
     */
    public void setInStore(final Boolean inStore) {
        this.inStore = inStore;
    }

    /**
     * @param into
     *            the into to set
     */
    public void setInto(final List<String> into) {
        this.into = into;
    }

    /**
     * @param maps
     *            the maps to set
     */
    public void setMaps(final Map<String, Boolean> maps) {
        this.maps = maps;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param plaintext
     *            the plaintext to set
     */
    public void setPlaintext(final String plaintext) {
        this.plaintext = plaintext;
    }

    /**
     * @param requiredChampion
     *            the requiredChampion to set
     */
    public void setRequiredChampion(final String requiredChampion) {
        this.requiredChampion = requiredChampion;
    }

    /**
     * @param rune
     *            the rune to set
     */
    public void setRune(final MetaData rune) {
        this.rune = rune;
    }

    /**
     * @param sanitizedDescription
     *            the sanitizedDescription to set
     */
    public void setSanitizedDescription(final String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * @param specialRecipe
     *            the specialRecipe to set
     */
    public void setSpecialRecipe(final Integer specialRecipe) {
        this.specialRecipe = specialRecipe;
    }

    /**
     * @param stacks
     *            the stacks to set
     */
    public void setStacks(final Integer stacks) {
        this.stacks = stacks;
    }

    /**
     * @param stats
     *            the stats to set
     */
    public void setStats(final BasicDataStats stats) {
        this.stats = stats;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(final List<String> tags) {
        this.tags = tags;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BasicData [colloq=" + colloq + ", description=" + description + ", group=" + group + ", name=" + name + ", plaintext=" + plaintext
                + ", requiredChampion=" + requiredChampion + ", sanitizedDescription=" + sanitizedDescription + ", consumeOnFull=" + consumeOnFull
                + ", consumed=" + consumed + ", hideFromAll=" + hideFromAll + ", inStore=" + inStore + ", depth=" + depth + ", id=" + id + ", specialRecipe="
                + specialRecipe + ", stacks=" + stacks + ", from=" + from + ", into=" + into + ", tags=" + tags + ", gold=" + gold + ", image=" + image
                + ", maps=" + maps + ", rune=" + rune + ", stats=" + stats + "]";
    }
}
