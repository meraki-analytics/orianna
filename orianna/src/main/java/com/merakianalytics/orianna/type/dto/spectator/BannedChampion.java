package com.merakianalytics.orianna.type.dto.spectator;

import com.merakianalytics.orianna.type.dto.DataObject;

public class BannedChampion extends DataObject {
    private static final long serialVersionUID = -8791690504376358795L;
    private long championId, teamId;
    private int pickTurn;

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
        final BannedChampion other = (BannedChampion)obj;
        if(championId != other.championId) {
            return false;
        }
        if(pickTurn != other.pickTurn) {
            return false;
        }
        if(teamId != other.teamId) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public long getChampionId() {
        return championId;
    }

    /**
     * @return the pickTurn
     */
    public int getPickTurn() {
        return pickTurn;
    }

    /**
     * @return the teamId
     */
    public long getTeamId() {
        return teamId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(championId ^ championId >>> 32);
        result = prime * result + pickTurn;
        result = prime * result + (int)(teamId ^ teamId >>> 32);
        return result;
    }

    /**
     * @param championId
     *        the championId to set
     */
    public void setChampionId(final long championId) {
        this.championId = championId;
    }

    /**
     * @param pickTurn
     *        the pickTurn to set
     */
    public void setPickTurn(final int pickTurn) {
        this.pickTurn = pickTurn;
    }

    /**
     * @param teamId
     *        the teamId to set
     */
    public void setTeamId(final long teamId) {
        this.teamId = teamId;
    }
}
