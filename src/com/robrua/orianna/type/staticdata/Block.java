package com.robrua.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class Block implements Serializable {
    private static final long serialVersionUID = 4057814343172931918L;
    public final List<BlockItem> items;
    public final Boolean recMath;
    public final String type;

    public Block(final List<BlockItem> items, final Boolean recMath, final String type) {
        this.items = items;
        this.recMath = recMath;
        this.type = type;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Block)) {
            return false;
        }
        final Block other = (Block)obj;
        if(items == null) {
            if(other.items != null) {
                return false;
            }
        }
        else if(!items.equals(other.items)) {
            return false;
        }
        if(recMath == null) {
            if(other.recMath != null) {
                return false;
            }
        }
        else if(!recMath.equals(other.recMath)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        }
        else if(!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (items == null ? 0 : items.hashCode());
        result = prime * result + (recMath == null ? 0 : recMath.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Block";
    }
}
