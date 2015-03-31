package com.robrua.orianna.type.dto.staticdata;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "runelist")
public class RuneList extends OriannaDto {
    private static final long serialVersionUID = -7089873705936509856L;
    @OneToOne(cascade = CascadeType.ALL)
    private BasicData basic;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, Rune> data;

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
        if(!(obj instanceof RuneList)) {
            return false;
        }
        final RuneList other = (RuneList)obj;
        if(basic == null) {
            if(other.basic != null) {
                return false;
            }
        }
        else if(!basic.equals(other.basic)) {
            return false;
        }
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
     * @return the basic
     */
    public BasicData getBasic() {
        return basic;
    }

    /**
     * @return the data
     */
    public Map<String, Rune> getData() {
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
        result = prime * result + (basic == null ? 0 : basic.hashCode());
        result = prime * result + (data == null ? 0 : data.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param basic
     *            the basic to set
     */
    public void setBasic(final BasicData basic) {
        this.basic = basic;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final Map<String, Rune> data) {
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
        return "RuneList [basic=" + basic + ", data=" + data + ", type=" + type + ", version=" + version + "]";
    }
}
