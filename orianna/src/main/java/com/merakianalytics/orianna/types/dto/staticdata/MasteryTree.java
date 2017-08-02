package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MasteryTree extends DataObject {
    private static final long serialVersionUID = -1580961535611704744L;
    private MasteryTreeList Resolve, Ferocity, Cunning;

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
        final MasteryTree other = (MasteryTree)obj;
        if(Cunning == null) {
            if(other.Cunning != null) {
                return false;
            }
        } else if(!Cunning.equals(other.Cunning)) {
            return false;
        }
        if(Ferocity == null) {
            if(other.Ferocity != null) {
                return false;
            }
        } else if(!Ferocity.equals(other.Ferocity)) {
            return false;
        }
        if(Resolve == null) {
            if(other.Resolve != null) {
                return false;
            }
        } else if(!Resolve.equals(other.Resolve)) {
            return false;
        }
        return true;
    }

    /**
     * @return the cunning
     */
    public MasteryTreeList getCunning() {
        return Cunning;
    }

    /**
     * @return the ferocity
     */
    public MasteryTreeList getFerocity() {
        return Ferocity;
    }

    /**
     * @return the resolve
     */
    public MasteryTreeList getResolve() {
        return Resolve;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (Cunning == null ? 0 : Cunning.hashCode());
        result = prime * result + (Ferocity == null ? 0 : Ferocity.hashCode());
        result = prime * result + (Resolve == null ? 0 : Resolve.hashCode());
        return result;
    }

    /**
     * @param cunning
     *        the cunning to set
     */
    public void setCunning(final MasteryTreeList cunning) {
        Cunning = cunning;
    }

    /**
     * @param ferocity
     *        the ferocity to set
     */
    public void setFerocity(final MasteryTreeList ferocity) {
        Ferocity = ferocity;
    }

    /**
     * @param resolve
     *        the resolve to set
     */
    public void setResolve(final MasteryTreeList resolve) {
        Resolve = resolve;
    }
}
