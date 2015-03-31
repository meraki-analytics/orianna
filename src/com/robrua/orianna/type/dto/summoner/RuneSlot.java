package com.robrua.orianna.type.dto.summoner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "runeslot")
public class RuneSlot extends OriannaDto {
    private static final long serialVersionUID = 3235577434750626183L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Integer runeId, runeSlotId;

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
        if(!(obj instanceof RuneSlot)) {
            return false;
        }
        final RuneSlot other = (RuneSlot)obj;
        if(runeId == null) {
            if(other.runeId != null) {
                return false;
            }
        }
        else if(!runeId.equals(other.runeId)) {
            return false;
        }
        if(runeSlotId == null) {
            if(other.runeSlotId != null) {
                return false;
            }
        }
        else if(!runeSlotId.equals(other.runeSlotId)) {
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
     * @return the runeId
     */
    public Integer getRuneId() {
        return runeId;
    }

    /**
     * @return the runeSlotId
     */
    public Integer getRuneSlotId() {
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
        result = prime * result + (runeId == null ? 0 : runeId.hashCode());
        result = prime * result + (runeSlotId == null ? 0 : runeSlotId.hashCode());
        return result;
    }

    /**
     * @param runeId
     *            the runeId to set
     */
    public void setRuneId(final Integer runeId) {
        this.runeId = runeId;
    }

    /**
     * @param runeSlotId
     *            the runeSlotId to set
     */
    public void setRuneSlotId(final Integer runeSlotId) {
        this.runeSlotId = runeSlotId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RuneSlot [runeId=" + runeId + ", runeSlotId=" + runeSlotId + "]";
    }
}
