package com.robrua.orianna.type.core.currentgame;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.exception.MissingDataException;

public class RuneCount extends OriannaObject<com.robrua.orianna.type.dto.currentgame.Rune> {
    private static final long serialVersionUID = 6466043229440089519L;
    private Rune rune;

    /**
     * @param data
     *            the underlying dto
     */
    public RuneCount(final com.robrua.orianna.type.dto.currentgame.Rune data) {
        super(data, com.robrua.orianna.type.dto.currentgame.Rune.class);
    }

    /**
     * The count of this rune used by the participant
     *
     * @return the count of this rune used by the participant
     */
    public int getCount() {
        return super.getInteger(data.getCount());
    }

    /**
     * The rune
     *
     * @return the rune
     */
    public Rune getRune() {
        if(rune != null) {
            return rune;
        }

        final Long l = data.getRuneId();
        if(l == null) {
            throw new MissingDataException("Rune ID is null.");
        }

        rune = RiotAPI.getRune(l.intValue());
        return rune;
    }

    /**
     * The ID of the rune
     *
     * @return the ID of the rune
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
        return "RuneCount (" + getRune() + " x " + getCount() + ")";
    }
}
