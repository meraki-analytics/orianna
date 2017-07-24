package com.merakianalytics.orianna.type.dto.match;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Mastery extends DataObject {
    private static final long serialVersionUID = -8156644601240081125L;
    private int masteryId, rank;

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
        final Mastery other = (Mastery)obj;
        if(masteryId != other.masteryId) {
            return false;
        }
        if(rank != other.rank) {
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
     * @return the rank
     */
    public int getRank() {
        return rank;
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
        result = prime * result + rank;
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
     * @param rank
     *        the rank to set
     */
    public void setRank(final int rank) {
        this.rank = rank;
    }
}
