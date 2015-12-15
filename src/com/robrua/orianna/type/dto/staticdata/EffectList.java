package com.robrua.orianna.type.dto.staticdata;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "effectlist")
public class EffectList extends OriannaDto {
    private static final long serialVersionUID = 572382532327504732L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @ElementCollection
    @CollectionTable(name = "effectlist_list", joinColumns = @JoinColumn(name = "effectlist_id") )
    private List<Double> list;

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
        if(!(obj instanceof EffectList)) {
            return false;
        }
        final EffectList other = (EffectList)obj;
        if(list == null) {
            if(other.list != null) {
                return false;
            }
        }
        else if(!list.equals(other.list)) {
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
     * @return the list
     */
    public List<Double> getList() {
        return list;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (list == null ? 0 : list.hashCode());
        return result;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(final List<Double> list) {
        this.list = list;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if(list == null) {
            return "null";
        }

        return list.toString();
    }
}
