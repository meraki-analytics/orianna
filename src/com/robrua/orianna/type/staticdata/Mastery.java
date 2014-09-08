package com.robrua.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class Mastery implements Serializable {
    private static final long serialVersionUID = -1142023772643801974L;
    public final List<String> description, sanitizedDescription;
    public final Integer ID, ranks;
    public final Image image;
    public final String name, prereq;

    public Mastery(final List<String> description, final List<String> sanitizedDescription, final Integer ID, final Integer ranks, final Image image,
            final String name, final String prereq) {
        this.description = description;
        this.sanitizedDescription = sanitizedDescription;
        this.ID = ID;
        this.ranks = ranks;
        this.image = image;
        this.name = name;
        this.prereq = prereq;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Mastery)) {
            return false;
        }
        final Mastery other = (Mastery)obj;
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
