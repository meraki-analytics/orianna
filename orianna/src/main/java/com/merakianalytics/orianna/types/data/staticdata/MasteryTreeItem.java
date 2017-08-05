package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class MasteryTreeItem extends CoreData {
    private static final long serialVersionUID = 1994278995376469638L;
    private int id;
    private int prerequisite;

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
        if(prerequisite != other.prerequisite) {
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
     * @return the prerequisite
     */
    public int getPrerequisite() {
        return prerequisite;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + prerequisite;
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
     * @param prerequisite
     *        the prerequisite to set
     */
    public void setPrerequisite(final int prerequisite) {
        this.prerequisite = prerequisite;
    }
}
