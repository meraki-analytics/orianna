package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MetaData extends DataObject {
    private static final long serialVersionUID = -8355230186641780544L;
    private boolean isRune;
    private String tier, type;

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
        final MetaData other = (MetaData)obj;
        if(isRune != other.isRune) {
            return false;
        }
        if(tier == null) {
            if(other.tier != null) {
                return false;
            }
        } else if(!tier.equals(other.tier)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        } else if(!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    /**
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isRune ? 1231 : 1237);
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @return the isRune
     */
    public boolean isRune() {
        return isRune;
    }

    /**
     * @param isRune
     *        the isRune to set
     */
    public void setRune(final boolean isRune) {
        this.isRune = isRune;
    }

    /**
     * @param tier
     *        the tier to set
     */
    public void setTier(final String tier) {
        this.tier = tier;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }
}
