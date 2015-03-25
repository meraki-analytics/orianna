package com.robrua.orianna.type.core.currentgame;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Side;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.exception.MissingDataException;

public class BannedChampion extends OriannaObject<com.robrua.orianna.type.dto.currentgame.BannedChampion> {
    private static final long serialVersionUID = -6508689007548363848L;
    private Champion champion;

    /**
     * @param data
     *            the underlying dto
     */
    public BannedChampion(final com.robrua.orianna.type.dto.currentgame.BannedChampion data) {
        super(data, com.robrua.orianna.type.dto.currentgame.BannedChampion.class);
    }

    /**
     * Gets the champion that was banned
     *
     * @return the champion that was banned
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Long l = data.getChampionId();
        if(l == null) {
            throw new MissingDataException("Champion ID is null.");
        }

        champion = RiotAPI.getChampionByID(l.longValue());
        return champion;
    }

    /**
     * The ID of the banned champion
     *
     * @return the ID of the banned champion
     */
    public long getChampionID() {
        return super.getLong(data.getChampionId());
    }

    /**
     * The turn during which the champion was banned
     *
     * @return the turn during which the champion was banned
     */
    public int getPickTurn() {
        return super.getInteger(data.getPickTurn());
    }

    /**
     * The team that banned the champion
     *
     * @return the team that banned the champion
     */
    public Side getTeam() {
        return Side.forID(super.getLong(data.getTeamId()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BannedChampion (" + getChampion() + ")";
    }
}
