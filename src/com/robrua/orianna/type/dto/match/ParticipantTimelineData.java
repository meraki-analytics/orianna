package com.robrua.orianna.type.dto.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "participanttimelinedata")
public class ParticipantTimelineData extends OriannaDto {
    private static final long serialVersionUID = 7842605945477094887L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Double tenToTwenty, thirtyToEnd, twentyToThirty, zeroToTen;

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
        if(!(obj instanceof ParticipantTimelineData)) {
            return false;
        }
        final ParticipantTimelineData other = (ParticipantTimelineData)obj;
        if(tenToTwenty == null) {
            if(other.tenToTwenty != null) {
                return false;
            }
        }
        else if(!tenToTwenty.equals(other.tenToTwenty)) {
            return false;
        }
        if(thirtyToEnd == null) {
            if(other.thirtyToEnd != null) {
                return false;
            }
        }
        else if(!thirtyToEnd.equals(other.thirtyToEnd)) {
            return false;
        }
        if(twentyToThirty == null) {
            if(other.twentyToThirty != null) {
                return false;
            }
        }
        else if(!twentyToThirty.equals(other.twentyToThirty)) {
            return false;
        }
        if(zeroToTen == null) {
            if(other.zeroToTen != null) {
                return false;
            }
        }
        else if(!zeroToTen.equals(other.zeroToTen)) {
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
     * @return the tenToTwenty
     */
    public Double getTenToTwenty() {
        return tenToTwenty;
    }

    /**
     * @return the thirtyToEnd
     */
    public Double getThirtyToEnd() {
        return thirtyToEnd;
    }

    /**
     * @return the twentyToThirty
     */
    public Double getTwentyToThirty() {
        return twentyToThirty;
    }

    /**
     * @return the zeroToTen
     */
    public Double getZeroToTen() {
        return zeroToTen;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (tenToTwenty == null ? 0 : tenToTwenty.hashCode());
        result = prime * result + (thirtyToEnd == null ? 0 : thirtyToEnd.hashCode());
        result = prime * result + (twentyToThirty == null ? 0 : twentyToThirty.hashCode());
        result = prime * result + (zeroToTen == null ? 0 : zeroToTen.hashCode());
        return result;
    }

    /**
     * @param tenToTwenty
     *            the tenToTwenty to set
     */
    public void setTenToTwenty(final Double tenToTwenty) {
        this.tenToTwenty = tenToTwenty;
    }

    /**
     * @param thirtyToEnd
     *            the thirtyToEnd to set
     */
    public void setThirtyToEnd(final Double thirtyToEnd) {
        this.thirtyToEnd = thirtyToEnd;
    }

    /**
     * @param twentyToThirty
     *            the twentyToThirty to set
     */
    public void setTwentyToThirty(final Double twentyToThirty) {
        this.twentyToThirty = twentyToThirty;
    }

    /**
     * @param zeroToTen
     *            the zeroToTen to set
     */
    public void setZeroToTen(final Double zeroToTen) {
        this.zeroToTen = zeroToTen;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantTimelineData [tenToTwenty=" + tenToTwenty + ", thirtyToEnd=" + thirtyToEnd + ", twentyToThirty=" + twentyToThirty + ", zeroToTen="
                + zeroToTen + "]";
    }
}
