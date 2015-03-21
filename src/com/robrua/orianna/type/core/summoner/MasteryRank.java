package com.robrua.orianna.type.core.summoner;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.exception.MissingDataException;

public class MasteryRank extends OriannaObject<com.robrua.orianna.type.dto.summoner.Mastery> {
    private static final long serialVersionUID = 2625639856219721648L;
    private Mastery mastery;

    /**
     * @param data
     *            the underlying dto
     */
    public MasteryRank(final com.robrua.orianna.type.dto.summoner.Mastery data) {
        super(data, com.robrua.orianna.type.dto.summoner.Mastery.class);
    }

    /**
     * Mastery this holds rank for
     *
     * @return mastery this holds rank for
     */
    public Mastery getMastery() {
        if(mastery != null) {
            return mastery;
        }

        final Integer i = data.getId();
        if(i == null) {
            throw new MissingDataException("Mastery ID is null.");
        }

        mastery = RiotAPI.getMastery(i.longValue());
        return mastery;
    }

    /**
     * ID for the Mastery this holds rank for
     *
     * @return ID for the mastery this holds rank for
     */
    public long getMasteryID() {
        return super.getInteger(data.getId());
    }

    /**
     * Mastery rank (i.e., the number of points put into this mastery)
     *
     * @return mastery rank (i.e., the number of points put into this mastery)
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
        return "MasteryRank (" + getMastery() + " level " + getRank() + ")";
    }
}
