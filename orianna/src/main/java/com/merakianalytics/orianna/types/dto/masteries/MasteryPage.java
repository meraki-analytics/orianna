package com.merakianalytics.orianna.types.dto.masteries;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MasteryPage extends DataObject {
    private static final long serialVersionUID = -5448618682355870894L;
    private boolean current;
    private long id;
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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final MasteryPage other = (MasteryPage)obj;
        if(current != other.current) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        } else if(!masteries.equals(other.masteries)) {
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
        result = prime * result + (current ? 1231 : 1237);
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
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
     * @param masteries
     *        the masteries to set
     */
    public void setMasteries(final List<Mastery> masteries) {
        this.masteries = masteries;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }
}
