package com.robrua.orianna.type.dto.status;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "shardstatus")
public class ShardStatus extends OriannaDto {
    private static final long serialVersionUID = 7618827332112337640L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private String hostname, name, region_tag, slug;

    @ElementCollection
    @CollectionTable(name = "shardstatus_locale", joinColumns = @JoinColumn(name = "shardstatus_id") )
    private List<String> locales;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> services;

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
        if(!(obj instanceof ShardStatus)) {
            return false;
        }
        final ShardStatus other = (ShardStatus)obj;
        if(hostname == null) {
            if(other.hostname != null) {
                return false;
            }
        }
        else if(!hostname.equals(other.hostname)) {
            return false;
        }
        if(locales == null) {
            if(other.locales != null) {
                return false;
            }
        }
        else if(!locales.equals(other.locales)) {
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
        if(region_tag == null) {
            if(other.region_tag != null) {
                return false;
            }
        }
        else if(!region_tag.equals(other.region_tag)) {
            return false;
        }
        if(services == null) {
            if(other.services != null) {
                return false;
            }
        }
        else if(!services.equals(other.services)) {
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
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @return the locales
     */
    public List<String> getLocales() {
        return locales;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the region_tag
     */
    public String getRegion_tag() {
        return region_tag;
    }

    /**
     * @return the services
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (hostname == null ? 0 : hostname.hashCode());
        result = prime * result + (locales == null ? 0 : locales.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (region_tag == null ? 0 : region_tag.hashCode());
        result = prime * result + (services == null ? 0 : services.hashCode());
        result = prime * result + (slug == null ? 0 : slug.hashCode());
        return result;
    }

    /**
     * @param hostname
     *            the hostname to set
     */
    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    /**
     * @param locales
     *            the locales to set
     */
    public void setLocales(final List<String> locales) {
        this.locales = locales;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param region_tag
     *            the region_tag to set
     */
    public void setRegion_tag(final String region_tag) {
        this.region_tag = region_tag;
    }

    /**
     * @param services
     *            the services to set
     */
    public void setServices(final List<Service> services) {
        this.services = services;
    }

    /**
     * @param slug
     *            the slug to set
     */
    public void setSlug(final String slug) {
        this.slug = slug;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShardStatus [hostname=" + hostname + ", name=" + name + ", region_tag=" + region_tag + ", slug=" + slug + ", locales=" + locales + ", services="
                + services + "]";
    }
}
