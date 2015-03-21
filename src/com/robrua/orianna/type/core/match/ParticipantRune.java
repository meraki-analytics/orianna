package com.robrua.orianna.type.core.match;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.exception.MissingDataException;

public class ParticipantRune extends OriannaObject<com.robrua.orianna.type.dto.match.Rune> {
    private static final long serialVersionUID = -8028177856067862738L;
    private Rune rune;

    /**
     * @param data
     *            the underlying dto
     */
    public ParticipantRune(final com.robrua.orianna.type.dto.match.Rune data) {
        super(data, com.robrua.orianna.type.dto.match.Rune.class);
    }

    /**
     * Rune count
     *
     * @return rune count
     */
    public long getCount() {
        return super.getLong(data.getRank());
    }

    /**
     * The rune this holds count for
     *
     * @return the rune this holds count for
     */
    public Rune getRune() {
        if(rune != null) {
            return rune;
        }

        final Long l = data.getRuneId();
        if(l == null) {
            throw new MissingDataException("Rune ID is null.");
        }

        rune = RiotAPI.getRune(l.longValue());
        return rune;
    }

    /**
     * The ID of the rune this holds count for
     *
     * @return the ID of the rune this holds count for
     */
    public long getRuneID() {
        return super.getLong(data.getRuneId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantRune (" + getRune() + "x" + getCount() + ")";
    }
}
