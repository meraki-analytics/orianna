package com.robrua.orianna.type.dto.status;

import java.util.List;

import com.robrua.orianna.type.dto.OriannaDto;

public class Service extends OriannaDto {
    private static final long serialVersionUID = -4726729009126605263L;
    private List<Incident> incident;
    private String name, slug, status;

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
        if(!(obj instanceof Service)) {
            return false;
        }
        final Service other = (Service)obj;
        if(incident == null) {
            if(other.incident != null) {
                return false;
            }
        }
        else if(!incident.equals(other.incident)) {
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
        if(slug == null) {
            if(other.slug != null) {
                return false;
            }
        }
        else if(!slug.equals(other.slug)) {
            return false;
        }
        if(status == null) {
            if(other.status != null) {
                return false;
            }
        }
        else if(!status.equals(other.status)) {
            return false;
        }
        return true;
    }

    /**
     * @return the incident
     */
    public List<Incident> getIncident() {
        return incident;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (incident == null ? 0 : incident.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (slug == null ? 0 : slug.hashCode());
        result = prime * result + (status == null ? 0 : status.hashCode());
        return result;
    }

    /**
     * @param incident
     *            the incident to set
     */
    public void setIncident(final List<Incident> incident) {
        this.incident = incident;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param slug
     *            the slug to set
     */
    public void setSlug(final String slug) {
        this.slug = slug;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Service [incident=" + incident + ", name=" + name + ", slug=" + slug + ", status=" + status + "]";
    }
}
