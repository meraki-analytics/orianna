package com.merakianalytics.orianna.type.dto.runes;

import java.util.Set;

import com.merakianalytics.orianna.type.dto.DataObject;

public class RunePage extends DataObject {
    private static final long serialVersionUID = -4627561419067702378L;
    private boolean current;
    private long id;
    private String name;
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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final RunePage other = (RunePage)obj;
        if(current != other.current) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(slots == null) {
            if(other.slots != null) {
                return false;
            }
        } else if(!slots.equals(other.slots)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public long getId() {
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
        result = prime * result + (current ? 1231 : 1237);
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (slots == null ? 0 : slots.hashCode());
        return result;
    }

    /**
     * @return the current
     */
    public boolean isCurrent() {
        return current;
    }

    /**
     * @param current
     *        the current to set
     */
    public void setCurrent(final boolean current) {
        this.current = current;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param slots
     *        the slots to set
     */
    public void setSlots(final Set<RuneSlot> slots) {
        this.slots = slots;
    }
}
