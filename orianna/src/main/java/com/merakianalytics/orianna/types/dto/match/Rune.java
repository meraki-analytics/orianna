package com.merakianalytics.orianna.types.dto.match;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Rune extends DataObject {
    private static final long serialVersionUID = 7083514158515535826L;
    private int runeId, rank;

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
        final Rune other = (Rune)obj;
        if(rank != other.rank) {
            return false;
        }
        if(runeId != other.runeId) {
            return false;
        }
        return true;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the runeId
     */
    public int getRuneId() {
        return runeId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rank;
        result = prime * result + runeId;
        return result;
    }

    /**
     * @param rank
     *        the rank to set
     */
    public void setRank(final int rank) {
        this.rank = rank;
    }

    /**
     * @param runeId
     *        the runeId to set
     */
    public void setRuneId(final int runeId) {
        this.runeId = runeId;
    }
}
