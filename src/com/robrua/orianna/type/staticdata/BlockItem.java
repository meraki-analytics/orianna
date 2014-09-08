package com.robrua.orianna.type.staticdata;

import java.io.Serializable;

public class BlockItem implements Serializable {
    private static final long serialVersionUID = 319237307142972812L;
    public final Integer count;
    public final Item item;

    public BlockItem(final Integer count, final Item item) {
        this.count = count;
        this.item = item;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof BlockItem)) {
            return false;
        }
        final BlockItem other = (BlockItem)obj;
        if(count == null) {
            if(other.count != null) {
                return false;
            }
        }
        else if(!count.equals(other.count)) {
            return false;
        }
        if(item == null) {
            if(other.item != null) {
                return false;
            }
        }
        else if(!item.equals(other.item)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (count == null ? 0 : count.hashCode());
        result = prime * result + (item == null ? 0 : item.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BlockItem [count=" + count + ", item=" + item + "]";
    }

}
