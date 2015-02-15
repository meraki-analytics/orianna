package com.robrua.orianna.type.core.league;

import com.robrua.orianna.type.core.OriannaObject;

public class MiniSeries extends OriannaObject<com.robrua.orianna.type.dto.league.MiniSeries> {
    private static final long serialVersionUID = -3621379268249106075L;

    /**
     * @param data
     *            the underlying dto
     */
    public MiniSeries(final com.robrua.orianna.type.dto.league.MiniSeries data) {
        super(data, com.robrua.orianna.type.dto.league.MiniSeries.class);
    }

    /**
     * Number of current losses in the mini series
     *
     * @return number of current losses in the mini series
     */
    public int getLosses() {
        return super.getInteger(data.getLosses());
    }

    /**
     * String showing the current, sequential mini series progress where 'W'
     * represents a win, 'L' represents a loss, and 'N' represents a game that
     * hasn't been played yet
     *
     * @return string showing the current, sequential mini series progress where
     *         'W' represents a win, 'L' represents a loss, and 'N' represents a
     *         game that hasn't been played yet
     */
    public String getProgress() {
        return super.getString(data.getProgress());
    }

    /**
     * Number of wins required for promotion
     *
     * @return number of wins required for promotion
     */
    public int getTarget() {
        return super.getInteger(data.getTarget());
    }

    /**
     * Number of current wins in the mini series
     *
     * @return number of current wins in the mini series
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
        return "MiniSeries (" + getProgress() + ")";
    }
}
