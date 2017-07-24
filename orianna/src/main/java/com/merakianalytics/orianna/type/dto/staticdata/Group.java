package com.merakianalytics.orianna.type.dto.staticdata;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Group extends DataObject {
    private static final long serialVersionUID = 590184784584559757L;
    private String MaxGroupOwnable, key;

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
        final Group other = (Group)obj;
        if(MaxGroupOwnable == null) {
            if(other.MaxGroupOwnable != null) {
                return false;
            }
        } else if(!MaxGroupOwnable.equals(other.MaxGroupOwnable)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        } else if(!key.equals(other.key)) {
            return false;
        }
        return true;
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
     *        the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param maxGroupOwnable
     *        the maxGroupOwnable to set
     */
    public void setMaxGroupOwnable(final String maxGroupOwnable) {
        MaxGroupOwnable = maxGroupOwnable;
    }
}
