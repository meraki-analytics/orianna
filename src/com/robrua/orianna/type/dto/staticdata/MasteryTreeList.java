package com.robrua.orianna.type.dto.staticdata;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "masterytreelist")
public class MasteryTreeList extends OriannaDto {
    private static final long serialVersionUID = -20489430038210081L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MasteryTreeItem> masteryTreeItems;

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
        if(!(obj instanceof MasteryTreeList)) {
            return false;
        }
        final MasteryTreeList other = (MasteryTreeList)obj;
        if(masteryTreeItems == null) {
            if(other.masteryTreeItems != null) {
                return false;
            }
        }
        else if(!masteryTreeItems.equals(other.masteryTreeItems)) {
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
     * @return the masteryTreeItems
     */
    public List<MasteryTreeItem> getMasteryTreeItems() {
        return masteryTreeItems;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (masteryTreeItems == null ? 0 : masteryTreeItems.hashCode());
        return result;
    }

    /**
     * @param masteryTreeItems
     *            the masteryTreeItems to set
     */
    public void setMasteryTreeItems(final List<MasteryTreeItem> masteryTreeItems) {
        this.masteryTreeItems = masteryTreeItems;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MasteryTreeList [masteryTreeItems=" + masteryTreeItems + "]";
    }
}
