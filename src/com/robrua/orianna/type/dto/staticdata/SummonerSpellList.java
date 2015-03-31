package com.robrua.orianna.type.dto.staticdata;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "summonerspelllist")
public class SummonerSpellList extends OriannaDto {
    private static final long serialVersionUID = 594583844266898980L;
    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, SummonerSpell> data;

    @Id
    private final long dbId = 0;

    private String type, version;

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
        if(!(obj instanceof SummonerSpellList)) {
            return false;
        }
        final SummonerSpellList other = (SummonerSpellList)obj;
        if(data == null) {
            if(other.data != null) {
                return false;
            }
        }
        else if(!data.equals(other.data)) {
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
    public Map<String, SummonerSpell> getData() {
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
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final Map<String, SummonerSpell> data) {
        this.data = data;
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
        return "SummonerSpellList [data=" + data + ", type=" + type + ", version=" + version + "]";
    }
}
