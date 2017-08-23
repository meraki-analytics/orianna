package com.merakianalytics.orianna.types.dto.status;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Incident extends DataObject {
    private static final long serialVersionUID = 3197042104669649311L;
    private boolean active;
    private String created_at;
    private long id;
    private List<Message> updates;

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
        final Incident other = (Incident)obj;
        if(active != other.active) {
            return false;
        }
        if(created_at == null) {
            if(other.created_at != null) {
                return false;
            }
        } else if(!created_at.equals(other.created_at)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(updates == null) {
            if(other.updates != null) {
                return false;
            }
        } else if(!updates.equals(other.updates)) {
            return false;
        }
        return true;
    }

    /**
     * @return the created_at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the updates
     */
    public List<Message> getUpdates() {
        return updates;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + (created_at == null ? 0 : created_at.hashCode());
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (updates == null ? 0 : updates.hashCode());
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
     * @param created_at
     *        the created_at to set
     */
    public void setCreated_at(final String created_at) {
        this.created_at = created_at;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @param updates
     *        the updates to set
     */
    public void setUpdates(final List<Message> updates) {
        this.updates = updates;
    }
}
