package com.robrua.orianna.type.core.match;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.exception.MissingDataException;

public class BannedChampion extends OriannaObject<com.robrua.orianna.type.dto.match.BannedChampion> {
    private static final long serialVersionUID = 8617936753352196228L;
    private Champion champion;

    /**
     * @param data
     *            the underlying dto
     */
    public BannedChampion(final com.robrua.orianna.type.dto.match.BannedChampion data) {
        super(data, com.robrua.orianna.type.dto.match.BannedChampion.class);
    }

    /**
     * Banned champion
     *
     * @return banned champion
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Integer i = data.getChampionId();
        if(i == null) {
            throw new MissingDataException("Champion ID is null.");
        }

        champion = RiotAPI.getChampionByID(i.longValue());
        return champion;
    }

    /**
     * Banned champion ID
     *
     * @return banned champion ID
     */
    public long getChampionID() {
        return super.getInteger(data.getChampionId());
    }

    /**
     * Turn during which the champion was banned
     *
     * @return turn during which the champion was banned
     */
    public int getPickTurn() {
        return super.getInteger(data.getPickTurn());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BannedChampion (" + getChampion() + " @ turn " + getPickTurn() + ")";
    }
}
