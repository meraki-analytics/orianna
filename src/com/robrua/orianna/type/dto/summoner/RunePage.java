package com.robrua.orianna.type.dto.summoner;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "runepage")
public class RunePage extends OriannaDto {
    private static final long serialVersionUID = 4197280778538512349L;
    private Boolean current;
    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<RuneSlot> slots;

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
        if(!(obj instanceof RunePage)) {
            return false;
        }
        final RunePage other = (RunePage)obj;
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
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(slots == null) {
            if(other.slots != null) {
                return false;
            }
        }
        else if(!slots.equals(other.slots)) {
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the slots
     */
    public Set<RuneSlot> getSlots() {
        return slots;
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
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (slots == null ? 0 : slots.hashCode());
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
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param slots
     *            the slots to set
     */
    public void setSlots(final Set<RuneSlot> slots) {
        this.slots = slots;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RunePage [id=" + id + ", current=" + current + ", name=" + name + ", slots=" + slots + "]";
    }
}
