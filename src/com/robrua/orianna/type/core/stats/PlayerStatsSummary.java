package com.robrua.orianna.type.core.stats;

import java.util.Date;

import com.robrua.orianna.type.core.OriannaObject;

public class PlayerStatsSummary extends OriannaObject<com.robrua.orianna.type.dto.stats.PlayerStatsSummary> {
    private static final long serialVersionUID = 3850401869706048479L;
    private AggregatedStats aggregatedStats;

    /**
     * @param data
     *            the underlying dto
     */
    public PlayerStatsSummary(final com.robrua.orianna.type.dto.stats.PlayerStatsSummary data) {
        super(data, com.robrua.orianna.type.dto.stats.PlayerStatsSummary.class);
    }

    /**
     * Aggregated stats
     *
     * @return aggregated stats
     */
    public AggregatedStats getAggregatedStats() {
        if(aggregatedStats == null) {
            aggregatedStats = new AggregatedStats(data.getAggregatedStats());
        }

        return aggregatedStats;
    }

    /**
     * Number of losses for this queue type. Returned for ranked queue types
     * only.
     *
     * @return number of losses for this queue type. Returned for ranked queue
     *         types only.
     */
    public int getLosses() {
        return super.getInteger(data.getLosses());
    }

    /**
     * Date stats were last modified
     *
     * @return date stats were last modified
     */
    public Date getModifyDate() {
        return super.getDate(data.getModifyDate());
    }

    /**
     * Player stats summary type
     *
     * @return player stats summary type
     */
    public PlayerStatsSummaryType getType() {
        return PlayerStatsSummaryType.valueOf(super.getString(data.getPlayerStatSummaryType()));
    }

    /**
     * Number of wins for this queue type
     *
     * @return number of wins for this queue type
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
        return "PlayerStatsSummary (" + getType() + ")";
    }
}
