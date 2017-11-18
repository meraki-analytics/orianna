package com.merakianalytics.orianna.types.dto.spectator;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Perks extends DataObject {
    private static final long serialVersionUID = 7440133663518034542L;
    private List<Long> perkIds;
    private long perkStyle, perkSubStyle;

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
        final Perks other = (Perks)obj;
        if(perkIds == null) {
            if(other.perkIds != null) {
                return false;
            }
        } else if(!perkIds.equals(other.perkIds)) {
            return false;
        }
        if(perkStyle != other.perkStyle) {
            return false;
        }
        if(perkSubStyle != other.perkSubStyle) {
            return false;
        }
        return true;
    }

    /**
     * @return the perkIds
     */
    public List<Long> getPerkIds() {
        return perkIds;
    }

    /**
     * @return the perkStyle
     */
    public long getPerkStyle() {
        return perkStyle;
    }

    /**
     * @return the perkSubStyle
     */
    public long getPerkSubStyle() {
        return perkSubStyle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (perkIds == null ? 0 : perkIds.hashCode());
        result = prime * result + (int)(perkStyle ^ perkStyle >>> 32);
        result = prime * result + (int)(perkSubStyle ^ perkSubStyle >>> 32);
        return result;
    }

    /**
     * @param perkIds
     *        the perkIds to set
     */
    public void setPerkIds(final List<Long> perkIds) {
        this.perkIds = perkIds;
    }

    /**
     * @param perkStyle
     *        the perkStyle to set
     */
    public void setPerkStyle(final long perkStyle) {
        this.perkStyle = perkStyle;
    }

    /**
     * @param perkSubStyle
     *        the perkSubStyle to set
     */
    public void setPerkSubStyle(final long perkSubStyle) {
        this.perkSubStyle = perkSubStyle;
    }
}
