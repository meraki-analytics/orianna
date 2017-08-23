package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class MasteryTree extends CoreData {
    private static final long serialVersionUID = 501183840631690977L;
    private List<MasteryTreeTier> resolve, ferocity, cunning;

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
        if(cunning == null) {
            if(other.cunning != null) {
                return false;
            }
        } else if(!cunning.equals(other.cunning)) {
            return false;
        }
        if(ferocity == null) {
            if(other.ferocity != null) {
                return false;
            }
        } else if(!ferocity.equals(other.ferocity)) {
            return false;
        }
        if(resolve == null) {
            if(other.resolve != null) {
                return false;
            }
        } else if(!resolve.equals(other.resolve)) {
            return false;
        }
        return true;
    }

    /**
     * @return the cunning
     */
    public List<MasteryTreeTier> getCunning() {
        return cunning;
    }

    /**
     * @return the ferocity
     */
    public List<MasteryTreeTier> getFerocity() {
        return ferocity;
    }

    /**
     * @return the resolve
     */
    public List<MasteryTreeTier> getResolve() {
        return resolve;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (cunning == null ? 0 : cunning.hashCode());
        result = prime * result + (ferocity == null ? 0 : ferocity.hashCode());
        result = prime * result + (resolve == null ? 0 : resolve.hashCode());
        return result;
    }

    /**
     * @param cunning
     *        the cunning to set
     */
    public void setCunning(final List<MasteryTreeTier> cunning) {
        this.cunning = cunning;
    }

    /**
     * @param ferocity
     *        the ferocity to set
     */
    public void setFerocity(final List<MasteryTreeTier> ferocity) {
        this.ferocity = ferocity;
    }

    /**
     * @param resolve
     *        the resolve to set
     */
    public void setResolve(final List<MasteryTreeTier> resolve) {
        this.resolve = resolve;
    }
}
