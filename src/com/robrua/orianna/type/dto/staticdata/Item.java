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
@Table(name = "item")
public class Item extends OriannaDto {
    private static final long serialVersionUID = -1354683419193009743L;
    private String colloq, name, requiredChampion;
    private Boolean consumeOnFull, consumed, hideFromAll, inStore;
    private Integer depth, specialRecipe, stacks;

    @Lob
    private String description, plaintext, sanitizedDescription;

    @ElementCollection
    @CollectionTable(name = "item_effect", joinColumns = @JoinColumn(name = "item_id") )
    private Map<String, String> effect;

    @ElementCollection
    @CollectionTable(name = "item_from", joinColumns = @JoinColumn(name = "item_id") )
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
    @CollectionTable(name = "item_into", joinColumns = @JoinColumn(name = "item_id") )
    @Column(name = "nto")
    private List<String> into;

    @ElementCollection
    @CollectionTable(name = "item_map", joinColumns = @JoinColumn(name = "item_id") )
    private Map<String, Boolean> maps;

    @OneToOne(cascade = CascadeType.ALL)
    private MetaData rune;

    @OneToOne(cascade = CascadeType.ALL)
    private BasicDataStats stats;

    @ElementCollection
    @CollectionTable(name = "item_tag", joinColumns = @JoinColumn(name = "item_id") )
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
        if(!(obj instanceof Item)) {
            return false;
        }
        final Item other = (Item)obj;
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
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
        if(keyType.equals(String.class)) {
            return "name";
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
        result = prime * result + (id == null ? 0 : id.hashCode());
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
     * @param effect
     *            the effect to set
     */
    public void setEffect(final Map<String, String> effect) {
        this.effect = effect;
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
        return "Item [colloq=" + colloq + ", description=" + description + ", group=" + group + ", name=" + name + ", plaintext=" + plaintext
                + ", requiredChampion=" + requiredChampion + ", sanitizedDescription=" + sanitizedDescription + ", consumeOnFull=" + consumeOnFull
                + ", consumed=" + consumed + ", hideFromAll=" + hideFromAll + ", inStore=" + inStore + ", depth=" + depth + ", id=" + id + ", specialRecipe="
                + specialRecipe + ", stacks=" + stacks + ", effect=" + effect + ", from=" + from + ", into=" + into + ", tags=" + tags + ", gold=" + gold
                + ", image=" + image + ", maps=" + maps + ", rune=" + rune + ", stats=" + stats + "]";
    }
}
