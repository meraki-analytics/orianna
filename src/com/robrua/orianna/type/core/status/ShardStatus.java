package com.robrua.orianna.type.core.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class ShardStatus extends OriannaObject<com.robrua.orianna.type.dto.status.ShardStatus> {
    private static final long serialVersionUID = 7842861515790025722L;
    private List<String> locales;
    private List<Service> services;

    /**
     * @param data
     *            the underlying dto
     */
    public ShardStatus(final com.robrua.orianna.type.dto.status.ShardStatus data) {
        super(data, com.robrua.orianna.type.dto.status.ShardStatus.class);
    }

    /**
     * The hostname
     *
     * @return the hostname
     */
    public String getHostname() {
        return super.getString(data.getHostname());
    }

    /**
     * The locales
     *
     * @return the locales
     */
    public List<String> getLocales() {
        if(locales == null) {
            locales = new ArrayList<>();
            locales.addAll(data.getLocales());
        }

        return Collections.unmodifiableList(locales);
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
     * The region tag
     *
     * @return the region tag
     */
    public String getRegionTag() {
        return super.getString(data.getRegion_tag());
    }

    /**
     * The services
     *
     * @return the services
     */
    public List<Service> getServices() {
        if(services == null) {
            services = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.status.Service service : data.getServices()) {
                services.add(new Service(service));
            }
        }

        return Collections.unmodifiableList(services);
    }

    /**
     * The slug
     *
     * @return the slug
     */
    public String getSlug() {
        return super.getString(data.getSlug());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShardStatus (" + getName() + ")";
    }
}
