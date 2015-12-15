package com.robrua.orianna.type.dto.staticdata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "staticdata.ChampionList")
@Table(name = "championlist")
public class ChampionList extends OriannaDto {
    private static final long serialVersionUID = -2563300402769670975L;
    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, Champion> data;

    @Id
    private final long dbId = 0;

    private String format, type, version;

    @ElementCollection
    @CollectionTable(name = "championlist_key", joinColumns = @JoinColumn(name = "championlist_id") )
    @Column(name = "keyy")
    private Map<String, String> keys;

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
        if(!(obj instanceof ChampionList)) {
            return false;
        }
        final ChampionList other = (ChampionList)obj;
        if(data == null) {
            if(other.data != null) {
                return false;
            }
        }
        else if(!data.equals(other.data)) {
            return false;
        }
        if(format == null) {
            if(other.format != null) {
                return false;
            }
        }
        else if(!format.equals(other.format)) {
            return false;
        }
        if(keys == null) {
            if(other.keys != null) {
                return false;
            }
        }
        else if(!keys.equals(other.keys)) {
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
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        }
        else if(!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    /**
     * @return the data
     */
    public Map<String, Champion> getData() {
        return data;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Gets all stored item IDs for batch lookup
     *
     * @return the item IDs
     */
    public Set<Long> getItemIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Champion champ : data.values()) {
            set.addAll(champ.getItemIDs());
        }

        return set;
    }

    /**
     * @return the keys
     */
    public Map<String, String> getKeys() {
        return keys;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (data == null ? 0 : data.hashCode());
        result = prime * result + (format == null ? 0 : format.hashCode());
        result = prime * result + (keys == null ? 0 : keys.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final Map<String, Champion> data) {
        this.data = data;
    }

    /**
     * @param format
     *            the format to set
     */
    public void setFormat(final String format) {
        this.format = format;
    }

    /**
     * @param keys
     *            the keys to set
     */
    public void setKeys(final Map<String, String> keys) {
        this.keys = keys;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChampionList [data=" + data + ", keys=" + keys + ", format=" + format + ", type=" + type + ", version=" + version + "]";
    }
}
