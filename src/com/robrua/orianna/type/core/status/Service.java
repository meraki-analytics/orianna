package com.robrua.orianna.type.core.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class Service extends OriannaObject<com.robrua.orianna.type.dto.status.Service> {
    private static final long serialVersionUID = 4726440822482124254L;
    private List<Incident> incidents;

    /**
     * @param data
     *            the underlying dto
     */
    public Service(final com.robrua.orianna.type.dto.status.Service data) {
        super(data, com.robrua.orianna.type.dto.status.Service.class);
    }

    /**
     * The incidents
     *
     * @return the incidents
     */
    public List<Incident> getIncidents() {
        if(incidents == null) {
            incidents = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.status.Incident incident : data.getIncidents()) {
                incidents.add(new Incident(incident));
            }
        }

        return Collections.unmodifiableList(incidents);
    }

    /**
     * The name
     *
     * @return the name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * The slug
     *
     * @return the slug
     */
    public String getSlug() {
        return super.getString(data.getSlug());
    }

    /**
     * The status
     *
     * @return the status
     */
    public String getStatus() {
        return super.getString(data.getStatus());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Service (" + getName() + ")";
    }
}
