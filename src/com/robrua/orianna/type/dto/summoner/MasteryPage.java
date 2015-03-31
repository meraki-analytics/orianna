package com.robrua.orianna.type.dto.summoner;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "masterypage")
public class MasteryPage extends OriannaDto {
    private static final long serialVersionUID = -1087013791899459444L;
    private Boolean current;
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mastery> masteries;

    private String name;

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
        if(!(obj instanceof MasteryPage)) {
            return false;
        }
        final MasteryPage other = (MasteryPage)obj;
        if(current == null) {
            if(other.current != null) {
                return false;
            }
        }
        else if(!current.equals(other.current)) {
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
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        }
        else if(!masteries.equals(other.masteries)) {
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
        return true;
    }

    /**
     * @return the current
     */
    public Boolean getCurrent() {
        return current;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "id";
        }
        return null;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the masteries
     */
    public List<Mastery> getMasteries() {
        return masteries;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (current == null ? 0 : current.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    /**
     * @param current
     *            the current to set
     */
    public void setCurrent(final Boolean current) {
        this.current = current;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param masteries
     *            the masteries to set
     */
    public void setMasteries(final List<Mastery> masteries) {
        this.masteries = masteries;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MasteryPage [current=" + current + ", id=" + id + ", masteries=" + masteries + ", name=" + name + "]";
    }
}
