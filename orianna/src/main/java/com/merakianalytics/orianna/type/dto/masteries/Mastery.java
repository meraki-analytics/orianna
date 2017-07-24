package com.merakianalytics.orianna.type.dto.masteries;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Mastery extends DataObject {
    private static final long serialVersionUID = 876705781890215069L;
    private int id, rank;

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
        if(id != other.id) {
            return false;
        }
        if(rank != other.rank) {
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
        result = prime * result + id;
        result = prime * result + rank;
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
     * @param rank
     *        the rank to set
     */
    public void setRank(final int rank) {
        this.rank = rank;
    }
}
