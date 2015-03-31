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
@Table(name = "block")
public class Block extends OriannaDto {
    private static final long serialVersionUID = 7157966924998816902L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BlockItem> items;

    private Boolean recMath;

    private String type;

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
        if(!(obj instanceof Block)) {
            return false;
        }
        final Block other = (Block)obj;
        if(items == null) {
            if(other.items != null) {
                return false;
            }
        }
        else if(!items.equals(other.items)) {
            return false;
        }
        if(recMath == null) {
            if(other.recMath != null) {
                return false;
            }
        }
        else if(!recMath.equals(other.recMath)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        }
        else if(!type.equals(other.type)) {
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
     * @return the items
     */
    public List<BlockItem> getItems() {
        return items;
    }

    /**
     * @return the recMath
     */
    public Boolean getRecMath() {
        return recMath;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (items == null ? 0 : items.hashCode());
        result = prime * result + (recMath == null ? 0 : recMath.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(final List<BlockItem> items) {
        this.items = items;
    }

    /**
     * @param recMath
     *            the recMath to set
     */
    public void setRecMath(final Boolean recMath) {
        this.recMath = recMath;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Block [items=" + items + ", recMath=" + recMath + ", type=" + type + "]";
    }
}
