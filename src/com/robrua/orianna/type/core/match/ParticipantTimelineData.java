package com.robrua.orianna.type.core.match;

import com.robrua.orianna.type.core.OriannaObject;

public class ParticipantTimelineData extends OriannaObject<com.robrua.orianna.type.dto.match.ParticipantTimelineData> {
    private static final long serialVersionUID = -2225368082512246008L;

    /**
     * @param data
     *            the underlying dto
     */
    public ParticipantTimelineData(final com.robrua.orianna.type.dto.match.ParticipantTimelineData data) {
        super(data, com.robrua.orianna.type.dto.match.ParticipantTimelineData.class);
    }

    /**
     * Value per minute from 10 min to 20 min
     *
     * @return value per minute from 10 min to 20 min
     */
    public double getTenToTwenty() {
        return super.getDouble(data.getTenToTwenty());
    }

    /**
     * Value per minute from 30 min to the end of the game
     *
     * @return value per minute from 30 min to the end of the game
     */
    public double getThirtyToEnd() {
        return super.getDouble(data.getThirtyToEnd());
    }

    /**
     * Value per minute from 20 min to 30 min
     *
     * @return value per minute from 20 min to 30 min
     */
    public double getTwentyToThirty() {
        return super.getDouble(data.getTwentyToThirty());
    }

    /**
     * Value per minute from the beginning of the game to 10 min
     *
     * @return value per minute from the beginning of the game to 10 min
     */
    public double getZeroToTen() {
        return super.getDouble(data.getZeroToTen());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantTimelineData (0-10: " + getZeroToTen() + ", 10-20: " + getTenToTwenty() + ", 20-30: " + getTwentyToThirty() + ", 30-end: "
                + getThirtyToEnd() + ")";
    }

}
