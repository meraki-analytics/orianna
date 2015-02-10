package com.robrua.orianna.type.dto.currentgame;

import com.robrua.orianna.type.dto.OriannaDto;

public class Rune extends OriannaDto {
    private static final long serialVersionUID = -2242219274680852910L;
    private Integer count;
    private Long runeId;

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
        if(!(obj instanceof Rune)) {
            return false;
        }
        final Rune other = (Rune)obj;
        if(count == null) {
            if(other.count != null) {
                return false;
            }
        }
        else if(!count.equals(other.count)) {
            return false;
        }
        if(runeId == null) {
            if(other.runeId != null) {
                return false;
            }
        }
        else if(!runeId.equals(other.runeId)) {
            return false;
        }
        return true;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @return the runeId
     */
    public Long getRuneId() {
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
        result = prime * result + (count == null ? 0 : count.hashCode());
        result = prime * result + (runeId == null ? 0 : runeId.hashCode());
        return result;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(final Integer count) {
        this.count = count;
    }

    /**
     * @param runeId
     *            the runeId to set
     */
    public void setRuneId(final Long runeId) {
        this.runeId = runeId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Rune [count=" + count + ", runeId=" + runeId + "]";
    }
}
