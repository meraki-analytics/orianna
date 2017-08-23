package com.merakianalytics.orianna.types.dto.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MasteryTreeList extends DataObject {
    private static final long serialVersionUID = -3220972376433060053L;
    private List<MasteryTreeItem> masteryTreeItems;

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
        final MasteryTreeList other = (MasteryTreeList)obj;
        if(masteryTreeItems == null) {
            if(other.masteryTreeItems != null) {
                return false;
            }
        } else if(!masteryTreeItems.equals(other.masteryTreeItems)) {
            return false;
        }
        return true;
    }

    /**
     * @return the masteryTreeItems
     */
    public List<MasteryTreeItem> getMasteryTreeItems() {
        return masteryTreeItems;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (masteryTreeItems == null ? 0 : masteryTreeItems.hashCode());
        return result;
    }

    /**
     * @param masteryTreeItems
     *        the masteryTreeItems to set
     */
    public void setMasteryTreeItems(final List<MasteryTreeItem> masteryTreeItems) {
        this.masteryTreeItems = masteryTreeItems;
    }
}
