package com.merakianalytics.orianna.types.dto.match;

import com.merakianalytics.orianna.types.dto.DataObject;

public class TeamBans extends DataObject {
    private static final long serialVersionUID = 7531863877099848361L;
    private int pickTurn, championId;

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
        final TeamBans other = (TeamBans)obj;
        if(championId != other.championId) {
            return false;
        }
        if(pickTurn != other.pickTurn) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public int getChampionId() {
        return championId;
    }

    /**
     * @return the pickTurn
     */
    public int getPickTurn() {
        return pickTurn;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + championId;
        result = prime * result + pickTurn;
        return result;
    }

    /**
     * @param championId
     *        the championId to set
     */
    public void setChampionId(final int championId) {
        this.championId = championId;
    }

    /**
     * @param pickTurn
     *        the pickTurn to set
     */
    public void setPickTurn(final int pickTurn) {
        this.pickTurn = pickTurn;
    }
}
