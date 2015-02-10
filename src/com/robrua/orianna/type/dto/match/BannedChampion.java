package com.robrua.orianna.type.dto.match;

import com.robrua.orianna.type.dto.OriannaDto;

public class BannedChampion extends OriannaDto {
    private static final long serialVersionUID = -4903663770220158361L;
    private Integer championId, pickTurn;

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
        if(!(obj instanceof BannedChampion)) {
            return false;
        }
        final BannedChampion other = (BannedChampion)obj;
        if(championId == null) {
            if(other.championId != null) {
                return false;
            }
        }
        else if(!championId.equals(other.championId)) {
            return false;
        }
        if(pickTurn == null) {
            if(other.pickTurn != null) {
                return false;
            }
        }
        else if(!pickTurn.equals(other.pickTurn)) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public Integer getChampionId() {
        return championId;
    }

    /**
     * @return the pickTurn
     */
    public Integer getPickTurn() {
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
        result = prime * result + (championId == null ? 0 : championId.hashCode());
        result = prime * result + (pickTurn == null ? 0 : pickTurn.hashCode());
        return result;
    }

    /**
     * @param championId
     *            the championId to set
     */
    public void setChampionId(final Integer championId) {
        this.championId = championId;
    }

    /**
     * @param pickTurn
     *            the pickTurn to set
     */
    public void setPickTurn(final Integer pickTurn) {
        this.pickTurn = pickTurn;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BannedChampion [championId=" + championId + ", pickTurn=" + pickTurn + "]";
    }
}
