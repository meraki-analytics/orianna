package com.merakianalytics.orianna.types.data.status;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class Service extends CoreData {
    private static final long serialVersionUID = -1389985537061774240L;
    private List<Incident> incidents;
    private String status, name;

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
        final Service other = (Service)obj;
        if(incidents == null) {
            if(other.incidents != null) {
                return false;
            }
        } else if(!incidents.equals(other.incidents)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(status == null) {
            if(other.status != null) {
                return false;
            }
        } else if(!status.equals(other.status)) {
            return false;
        }
        return true;
    }

    /**
     * @return the incidents
     */
    public List<Incident> getIncidents() {
        return incidents;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (incidents == null ? 0 : incidents.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (status == null ? 0 : status.hashCode());
        return result;
    }

    /**
     * @param incidents
     *        the incidents to set
     */
    public void setIncidents(final List<Incident> incidents) {
        this.incidents = incidents;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param status
     *        the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }
}
