package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "blockitem")
public class BlockItem extends OriannaDto {
    private static final long serialVersionUID = -183649437260660069L;
    private Integer count, id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

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
        if(!(obj instanceof BlockItem)) {
            return false;
        }
        final BlockItem other = (BlockItem)obj;
        if(count == null) {
            if(other.count != null) {
                return false;
            }
        }
        else if(!count.equals(other.count)) {
            return false;
        }
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (count == null ? 0 : count.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(final Integer count) {
        this.count = count;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BlockItem [count=" + count + ", id=" + id + "]";
    }
}
