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
@Table(name = "itemtree")
public class ItemTree extends OriannaDto {
    private static final long serialVersionUID = 5673226182296449779L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private String header;

    @ElementCollection
    @CollectionTable(name = "itemtree_tag", joinColumns = @JoinColumn(name = "itemtree_id") )
    private List<String> tags;

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
        if(!(obj instanceof ItemTree)) {
            return false;
        }
        final ItemTree other = (ItemTree)obj;
        if(header == null) {
            if(other.header != null) {
                return false;
            }
        }
        else if(!header.equals(other.header)) {
            return false;
        }
        if(tags == null) {
            if(other.tags != null) {
                return false;
            }
        }
        else if(!tags.equals(other.tags)) {
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
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (header == null ? 0 : header.hashCode());
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        return result;
    }

    /**
     * @param header
     *            the header to set
     */
    public void setHeader(final String header) {
        this.header = header;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(final List<String> tags) {
        this.tags = tags;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ItemTree [header=" + header + ", tags=" + tags + "]";
    }
}
