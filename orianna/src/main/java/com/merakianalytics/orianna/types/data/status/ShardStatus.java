package com.merakianalytics.orianna.types.data.status;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class ShardStatus extends CoreData {
    private static final long serialVersionUID = -3562231855274100852L;
    private List<String> locales;
    private String name, hostname, regionTag, slug, platform;
    private List<Service> services;

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
        final ShardStatus other = (ShardStatus)obj;
        if(hostname == null) {
            if(other.hostname != null) {
                return false;
            }
        } else if(!hostname.equals(other.hostname)) {
            return false;
        }
        if(locales == null) {
            if(other.locales != null) {
                return false;
            }
        } else if(!locales.equals(other.locales)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(regionTag == null) {
            if(other.regionTag != null) {
                return false;
            }
        } else if(!regionTag.equals(other.regionTag)) {
            return false;
        }
        if(services == null) {
            if(other.services != null) {
                return false;
            }
        } else if(!services.equals(other.services)) {
            return false;
        }
        if(slug == null) {
            if(other.slug != null) {
                return false;
            }
        } else if(!slug.equals(other.slug)) {
            return false;
        }
        return true;
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
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the regionTag
     */
    public String getRegionTag() {
        return regionTag;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (hostname == null ? 0 : hostname.hashCode());
        result = prime * result + (locales == null ? 0 : locales.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (regionTag == null ? 0 : regionTag.hashCode());
        result = prime * result + (services == null ? 0 : services.hashCode());
        result = prime * result + (slug == null ? 0 : slug.hashCode());
        return result;
    }

    /**
     * @param hostname
     *        the hostname to set
     */
    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    /**
     * @param locales
     *        the locales to set
     */
    public void setLocales(final List<String> locales) {
        this.locales = locales;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param regionTag
     *        the regionTag to set
     */
    public void setRegionTag(final String regionTag) {
        this.regionTag = regionTag;
    }

    /**
     * @param services
     *        the services to set
     */
    public void setServices(final List<Service> services) {
        this.services = services;
    }

    /**
     * @param slug
     *        the slug to set
     */
    public void setSlug(final String slug) {
        this.slug = slug;
    }
}
