package com.robrua.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Item implements Serializable {
    private static final long serialVersionUID = 3436663129646313824L;
    public final String colloq, description, group, name, plaintext, requiredChampion, sanitizedDescription;
    public final Boolean consumeOnFull, consumed, hideFromAll, inStore;
    public final Integer depth, ID, specialRecipe, stacks;
    public final List<String> from, into, tags;
    public final Gold gold;
    public final Image image;
    public final Map<String, Boolean> maps;
    public final MetaData rune;
    public final BasicDataStats stats;

    public Item(final String colloq, final String description, final String group, final String name, final String plaintext, final String requiredChampion,
            final String sanitizedDescription, final Boolean consumeOnFull, final Boolean consumed, final Boolean hideFromAll, final Boolean inStore,
            final Integer depth, final Integer ID, final Integer specialRecipe, final Integer stacks, final List<String> from, final List<String> into,
            final List<String> tags, final Gold gold, final Image image, final Map<String, Boolean> maps, final MetaData rune, final BasicDataStats stats) {
        this.colloq = colloq;
        this.description = description;
        this.group = group;
        this.name = name;
        this.plaintext = plaintext;
        this.requiredChampion = requiredChampion;
        this.sanitizedDescription = sanitizedDescription;
        this.consumeOnFull = consumeOnFull;
        this.consumed = consumed;
        this.hideFromAll = hideFromAll;
        this.inStore = inStore;
        this.depth = depth;
        this.ID = ID;
        this.specialRecipe = specialRecipe;
        this.stacks = stacks;
        this.from = from;
        this.into = into;
        this.tags = tags;
        this.gold = gold;
        this.image = image;
        this.maps = maps;
        this.rune = rune;
        this.stats = stats;
    }

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
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
