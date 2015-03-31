package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "grp")
public class Group extends OriannaDto {
    private static final long serialVersionUID = -6233511964798419336L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @Column(name = "keyy")
    private String key;

    private String MaxGroupOwnable;

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
        if(!(obj instanceof Group)) {
            return false;
        }
        final Group other = (Group)obj;
        if(MaxGroupOwnable == null) {
            if(other.MaxGroupOwnable != null) {
                return false;
            }
        }
        else if(!MaxGroupOwnable.equals(other.MaxGroupOwnable)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        }
        else if(!key.equals(other.key)) {
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
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the maxGroupOwnable
     */
    public String getMaxGroupOwnable() {
        return MaxGroupOwnable;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (MaxGroupOwnable == null ? 0 : MaxGroupOwnable.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        return result;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param maxGroupOwnable
     *            the maxGroupOwnable to set
     */
    public void setMaxGroupOwnable(final String maxGroupOwnable) {
        MaxGroupOwnable = maxGroupOwnable;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Group [MaxGroupOwnable=" + MaxGroupOwnable + ", key=" + key + "]";
    }
}
