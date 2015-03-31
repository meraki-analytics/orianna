package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "skin")
public class Skin extends OriannaDto {
    private static final long serialVersionUID = 1435516912904407890L;
    @Id
    private Integer id;
    private String name;

    private Integer num;

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
        if(!(obj instanceof Skin)) {
            return false;
        }
        final Skin other = (Skin)obj;
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(num == null) {
            if(other.num != null) {
                return false;
            }
        }
        else if(!num.equals(other.num)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Integer.class)) {
            return "id";
        }
        if(keyType.equals(String.class)) {
            return "name";
        }
        return null;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (num == null ? 0 : num.hashCode());
        return result;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param num
     *            the num to set
     */
    public void setNum(final Integer num) {
        this.num = num;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Skin [id=" + id + ", num=" + num + ", name=" + name + "]";
    }
}
