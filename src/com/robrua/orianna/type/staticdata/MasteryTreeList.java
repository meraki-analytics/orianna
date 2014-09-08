package com.robrua.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class MasteryTreeList implements Serializable {
    private static final long serialVersionUID = -1838778113529181171L;
    public final List<MasteryTreeItem> masteryTreeItems;

    public MasteryTreeList(final List<MasteryTreeItem> masteryTreeItems) {
        this.masteryTreeItems = masteryTreeItems;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MasteryTreeList)) {
            return false;
        }
        final MasteryTreeList other = (MasteryTreeList)obj;
        if(masteryTreeItems == null) {
            if(other.masteryTreeItems != null) {
                return false;
            }
        }
        else if(!masteryTreeItems.equals(other.masteryTreeItems)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (masteryTreeItems == null ? 0 : masteryTreeItems.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MasteryTreeList";
    }
}
