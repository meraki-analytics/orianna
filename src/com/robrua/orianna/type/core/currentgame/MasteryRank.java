package com.robrua.orianna.type.core.currentgame;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.exception.MissingDataException;

public class MasteryRank extends OriannaObject<com.robrua.orianna.type.dto.currentgame.Mastery> {
    private static final long serialVersionUID = -5941124817517722489L;
    private Mastery mastery;

    /**
     * @param data
     *            the underlying dto
     */
    public MasteryRank(final com.robrua.orianna.type.dto.currentgame.Mastery data) {
        super(data, com.robrua.orianna.type.dto.currentgame.Mastery.class);
    }

    /**
     * The mastery
     *
     * @return the mastery
     */
    public Mastery getMastery() {
        if(mastery != null) {
            return mastery;
        }

        final Long l = data.getMasteryId();
        if(l == null) {
            throw new MissingDataException("Mastery ID is null.");
        }

        mastery = RiotAPI.getMastery(l.longValue());
        return mastery;
    }

    /**
     * The ID of the mastery
     *
     * @return the ID of the mastery
     */
    public long getMasteryID() {
        return super.getLong(data.getMasteryId());
    }

    /**
     * The number of points put into this mastery by the user
     *
     * @return the number of points put into this mastery by the user
     */
    public int getRank() {
        return super.getInteger(data.getRank());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MasteryRank (" + getMastery() + " @ level " + getRank() + ")";
    }
}
