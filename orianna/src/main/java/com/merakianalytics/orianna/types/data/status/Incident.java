package com.merakianalytics.orianna.types.data.status;

import java.util.List;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.data.CoreData;

public class Incident extends CoreData {
    private static final long serialVersionUID = -8747080125041481057L;
    private boolean active;
    private DateTime created;
    private long id;
    private List<Message> updates;

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
        if(created == null) {
            if(other.created != null) {
                return false;
            }
        } else if(!created.equals(other.created)) {
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
     * @return the created
     */
    public DateTime getCreated() {
        return created;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + (created == null ? 0 : created.hashCode());
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
     * @param created
     *        the created to set
     */
    public void setCreated(final DateTime created) {
        this.created = created;
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
