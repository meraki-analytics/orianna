package com.merakianalytics.orianna.types.data.masteries;

import com.merakianalytics.orianna.types.data.CoreData;

public class MasteryPage extends CoreData.MapProxy<Integer, Integer> {
    private static final long serialVersionUID = 8966096502651402873L;
    private boolean active;
    private long id;
    private String name;

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
        final MasteryPage other = (MasteryPage)obj;
        if(active != other.active) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active
     *        the active to set
     */
    public void setActive(final boolean active) {
        this.active = active;
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
}
