package com.robrua.orianna.type.core.match;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.exception.MissingDataException;

public class ParticipantMastery extends OriannaObject<com.robrua.orianna.type.dto.match.Mastery> {
    private static final long serialVersionUID = 4180414467150787318L;
    private Mastery mastery;

    /**
     * @param data
     *            the underlying dto
     */
    public ParticipantMastery(final com.robrua.orianna.type.dto.match.Mastery data) {
        super(data, com.robrua.orianna.type.dto.match.Mastery.class);
    }

    /**
     * The mastery this holds rank for
     *
     * @return the mastery this holds rank for
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
     * The ID of the mastery this holds rank for
     *
     * @return the ID of the mastery this holds rank for
     */
    public long getMasteryID() {
        return super.getLong(data.getMasteryId());
    }

    /**
     * Mastery rank
     *
     * @return mastery rank
     */
    public long getRank() {
        return super.getLong(data.getRank());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantMastery (" + getMastery() + ", level " + getRank() + ")";
    }
}
