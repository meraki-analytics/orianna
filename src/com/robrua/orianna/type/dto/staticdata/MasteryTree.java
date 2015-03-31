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

    @Id
    private final long dbId = 0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "masterytree_id")
    private List<MasteryTreeList> Defense, Offense, Utility;

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
        if(Defense == null) {
            if(other.Defense != null) {
                return false;
            }
        }
        else if(!Defense.equals(other.Defense)) {
            return false;
        }
        if(Offense == null) {
            if(other.Offense != null) {
                return false;
            }
        }
        else if(!Offense.equals(other.Offense)) {
            return false;
        }
        if(Utility == null) {
            if(other.Utility != null) {
                return false;
            }
        }
        else if(!Utility.equals(other.Utility)) {
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
     * @return the defense
     */
    public List<MasteryTreeList> getDefense() {
        return Defense;
    }

    /**
     * @return the offense
     */
    public List<MasteryTreeList> getOffense() {
        return Offense;
    }

    /**
     * @return the utility
     */
    public List<MasteryTreeList> getUtility() {
        return Utility;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (Defense == null ? 0 : Defense.hashCode());
        result = prime * result + (Offense == null ? 0 : Offense.hashCode());
        result = prime * result + (Utility == null ? 0 : Utility.hashCode());
        return result;
    }

    /**
     * @param defense
     *            the defense to set
     */
    public void setDefense(final List<MasteryTreeList> defense) {
        Defense = defense;
    }

    /**
     * @param offense
     *            the offense to set
     */
    public void setOffense(final List<MasteryTreeList> offense) {
        Offense = offense;
    }

    /**
     * @param utility
     *            the utility to set
     */
    public void setUtility(final List<MasteryTreeList> utility) {
        Utility = utility;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MasteryTree [Defense=" + Defense + ", Offense=" + Offense + ", Utility=" + Utility + "]";
    }
}
