package com.merakianalytics.orianna.type.dto.staticdata;

import com.merakianalytics.orianna.type.dto.DataObject;

public class MasteryTreeItem extends DataObject {
    private static final long serialVersionUID = -8565981768467087946L;
    private int masteryId;
    private String prereq;

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
        final MasteryTreeItem other = (MasteryTreeItem)obj;
        if(masteryId != other.masteryId) {
            return false;
        }
        if(prereq == null) {
            if(other.prereq != null) {
                return false;
            }
        } else if(!prereq.equals(other.prereq)) {
            return false;
        }
        return true;
    }

    /**
     * @return the masteryId
     */
    public int getMasteryId() {
        return masteryId;
    }

    /**
     * @return the prereq
     */
    public String getPrereq() {
        return prereq;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + masteryId;
        result = prime * result + (prereq == null ? 0 : prereq.hashCode());
        return result;
    }

    /**
     * @param masteryId
     *        the masteryId to set
     */
    public void setMasteryId(final int masteryId) {
        this.masteryId = masteryId;
    }

    /**
     * @param prereq
     *        the prereq to set
     */
    public void setPrereq(final String prereq) {
        this.prereq = prereq;
    }
}
