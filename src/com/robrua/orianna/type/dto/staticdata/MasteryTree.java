package com.robrua.orianna.type.dto.staticdata;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "masterytree")
public class MasteryTree extends OriannaDto {
    private static final long serialVersionUID = -7530057902542747774L;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "masterytree_id")
    private List<MasteryTreeList> Cunning, Ferocity, Resolve;

    @Id
    private final long dbId = 0;

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
        if(!(obj instanceof MasteryTree)) {
            return false;
        }
        final MasteryTree other = (MasteryTree)obj;
        if(Cunning == null) {
            if(other.Cunning != null) {
                return false;
            }
        }
        else if(!Cunning.equals(other.Cunning)) {
            return false;
        }
        if(Ferocity == null) {
            if(other.Ferocity != null) {
                return false;
            }
        }
        else if(!Ferocity.equals(other.Ferocity)) {
            return false;
        }
        if(Resolve == null) {
            if(other.Resolve != null) {
                return false;
            }
        }
        else if(!Resolve.equals(other.Resolve)) {
            return false;
        }
        return true;
    }

    /**
     * @return the cunning
     */
    public List<MasteryTreeList> getCunning() {
        return Cunning;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the ferocity
     */
    public List<MasteryTreeList> getFerocity() {
        return Ferocity;
    }

    /**
     * @return the resolve
     */
    public List<MasteryTreeList> getResolve() {
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
     *            the cunning to set
     */
    public void setCunning(final List<MasteryTreeList> cunning) {
        Cunning = cunning;
    }

    /**
     * @param ferocity
     *            the ferocity to set
     */
    public void setFerocity(final List<MasteryTreeList> ferocity) {
        Ferocity = ferocity;
    }

    /**
     * @param resolve
     *            the resolve to set
     */
    public void setResolve(final List<MasteryTreeList> resolve) {
        Resolve = resolve;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MasteryTree [Cunning=" + Cunning + ", Ferocity=" + Ferocity + ", Resolve=" + Resolve + "]";
    }
}
