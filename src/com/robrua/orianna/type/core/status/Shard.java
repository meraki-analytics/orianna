package com.robrua.orianna.type.core.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class Shard extends OriannaObject<com.robrua.orianna.type.dto.status.Shard> {
    private static final long serialVersionUID = 6716759243691544929L;
    private List<String> locales;

    /**
     * @param data
     *            the underlying dto
     */
    public Shard(final com.robrua.orianna.type.dto.status.Shard data) {
        super(data, com.robrua.orianna.type.dto.status.Shard.class);
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
        return "Shard (" + getName() + ")";
    }
}
