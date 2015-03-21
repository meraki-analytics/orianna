package com.robrua.orianna.type.core.summoner;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.exception.MissingDataException;

public class RuneSlot extends OriannaObject<com.robrua.orianna.type.dto.summoner.RuneSlot> {
    private static final long serialVersionUID = -2885249708263800551L;
    private Rune rune;

    /**
     * @param data
     *            the underlying dto
     */
    public RuneSlot(final com.robrua.orianna.type.dto.summoner.RuneSlot data) {
        super(data, com.robrua.orianna.type.dto.summoner.RuneSlot.class);
    }

    /**
     * Rune associated with the rune slot
     *
     * @return rune associated with the rune slot
     */
    public Rune getRune() {
        if(rune != null) {
            return rune;
        }

        final Integer i = data.getRuneId();
        if(i == null) {
            throw new MissingDataException("Rune ID is null.");
        }

        rune = RiotAPI.getRune(i.longValue());
        return rune;
    }

    /**
     * Rune ID associated with the rune slot
     *
     * @return rune ID associated with the rune slot
     */
    public long getRuneID() {
        return super.getInteger(data.getRuneId());
    }

    /**
     * Rune slot ID
     *
     * @return rune slot ID
     */
    public int getRuneSlotID() {
        return super.getInteger(data.getRuneSlotId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RuneSlot (" + getRune() + ")";
    }
}
