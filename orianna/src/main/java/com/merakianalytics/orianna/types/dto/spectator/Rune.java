package com.merakianalytics.orianna.types.dto.spectator;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Rune extends DataObject {
    private static final long serialVersionUID = 6456009941954714881L;
    private int count;
    private long runeId;

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
        final Rune other = (Rune)obj;
        if(count != other.count) {
            return false;
        }
        if(runeId != other.runeId) {
            return false;
        }
        return true;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @return the runeId
     */
    public long getRuneId() {
        return runeId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + (int)(runeId ^ runeId >>> 32);
        return result;
    }

    /**
     * @param count
     *        the count to set
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * @param runeId
     *        the runeId to set
     */
    public void setRuneId(final long runeId) {
        this.runeId = runeId;
    }
}
