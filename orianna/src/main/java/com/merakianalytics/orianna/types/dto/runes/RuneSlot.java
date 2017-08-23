package com.merakianalytics.orianna.types.dto.runes;

import com.merakianalytics.orianna.types.dto.DataObject;

public class RuneSlot extends DataObject {
    private static final long serialVersionUID = 6210652599795398380L;
    private int runeSlotId, runeId;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final RuneSlot other = (RuneSlot)obj;
        if(runeId != other.runeId) {
            return false;
        }
        if(runeSlotId != other.runeSlotId) {
            return false;
        }
        return true;
    }

    /**
     * @return the runeId
     */
    public int getRuneId() {
        return runeId;
    }

    /**
     * @return the runeSlotId
     */
    public int getRuneSlotId() {
        return runeSlotId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + runeId;
        result = prime * result + runeSlotId;
        return result;
    }

    /**
     * @param runeId
     *        the runeId to set
     */
    public void setRuneId(final int runeId) {
        this.runeId = runeId;
    }

    /**
     * @param runeSlotId
     *        the runeSlotId to set
     */
    public void setRuneSlotId(final int runeSlotId) {
        this.runeSlotId = runeSlotId;
    }
}
