package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "metadata")
public class MetaData extends OriannaDto {
    private static final long serialVersionUID = 8478117741387975166L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private Boolean isRune;

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
        if(!(obj instanceof MetaData)) {
            return false;
        }
        final MetaData other = (MetaData)obj;
        if(isRune == null) {
            if(other.isRune != null) {
                return false;
            }
        }
        else if(!isRune.equals(other.isRune)) {
            return false;
        }
        if(tier == null) {
            if(other.tier != null) {
                return false;
            }
        }
        else if(!tier.equals(other.tier)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        }
        else if(!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the isRune
     */
    public Boolean getIsRune() {
        return isRune;
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
        result = prime * result + (isRune == null ? 0 : isRune.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @param isRune
     *            the isRune to set
     */
    public void setIsRune(final Boolean isRune) {
        this.isRune = isRune;
    }

    /**
     * @param tier
     *            the tier to set
     */
    public void setTier(final String tier) {
        this.tier = tier;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MetaData [isRune=" + isRune + ", tier=" + tier + ", type=" + type + "]";
    }
}
