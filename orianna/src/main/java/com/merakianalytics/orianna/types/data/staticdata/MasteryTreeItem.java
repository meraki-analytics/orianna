package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class MasteryTreeItem extends CoreData {
    private static final long serialVersionUID = 6992480779119108141L;
    private int id, prerequisiteId;

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
        final MasteryTreeItem other = (MasteryTreeItem)obj;
        if(id != other.id) {
            return false;
        }
        if(prerequisiteId != other.prerequisiteId) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the prerequisiteId
     */
    public int getPrerequisiteId() {
        return prerequisiteId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + prerequisiteId;
        return result;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param prerequisiteId
     *        the prerequisiteId to set
     */
    public void setPrerequisiteId(final int prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }
}
