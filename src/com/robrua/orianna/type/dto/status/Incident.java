package com.robrua.orianna.type.dto.status;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "incident")
public class Incident extends OriannaDto {
    private static final long serialVersionUID = 59206886346662528L;
    private Boolean active;
    private String created_at;

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
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
        if(!(obj instanceof Incident)) {
            return false;
        }
        final Incident other = (Incident)obj;
        if(active == null) {
            if(other.active != null) {
                return false;
            }
        }
        else if(!active.equals(other.active)) {
            return false;
        }
        if(created_at == null) {
            if(other.created_at != null) {
                return false;
            }
        }
        else if(!created_at.equals(other.created_at)) {
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
        if(updates == null) {
            if(other.updates != null) {
                return false;
            }
        }
        else if(!updates.equals(other.updates)) {
            return false;
        }
        return true;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @return the created_at
     */
    public String getCreated_at() {
        return created_at;
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
        result = prime * result + (active == null ? 0 : active.hashCode());
        result = prime * result + (created_at == null ? 0 : created_at.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (updates == null ? 0 : updates.hashCode());
        return result;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(final Boolean active) {
        this.active = active;
    }

    /**
     * @param created_at
     *            the created_at to set
     */
    public void setCreated_at(final String created_at) {
        this.created_at = created_at;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param updates
     *            the updates to set
     */
    public void setUpdates(final List<Message> updates) {
        this.updates = updates;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Incident [active=" + active + ", created_at=" + created_at + ", id=" + id + ", updates=" + updates + "]";
    }
}
