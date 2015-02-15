package com.robrua.orianna.type.core.team;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.QueueType;

public class TeamStatDetail extends OriannaObject<com.robrua.orianna.type.dto.team.TeamStatDetail> {
    private static final long serialVersionUID = -7615942950739158048L;

    /**
     * @param data
     *            the underlying dto
     */
    public TeamStatDetail(final com.robrua.orianna.type.dto.team.TeamStatDetail data) {
        super(data, com.robrua.orianna.type.dto.team.TeamStatDetail.class);
    }

    /**
     * Average games played
     *
     * @return average games played
     */
    public int getAverageGamesPlayed() {
        return super.getInteger(data.getAverageGamesPlayed());
    }

    /**
     * Number of losses
     *
     * @return number of losses
     */
    public int getLosses() {
        return super.getInteger(data.getLosses());
    }

    /**
     * Team stat type
     *
     * @return team stat type
     */
    public QueueType getTeamStatType() {
        return QueueType.valueOf(super.getString(data.getTeamStatType()));
    }

    /**
     * Number of wins
     *
     * @return number of wins
     */
    public int getWins() {
        return super.getInteger(data.getWins());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TeamStatDetail (" + getTeamStatType() + ")";
    }
}
