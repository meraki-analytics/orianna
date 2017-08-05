package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class Realm extends CoreData {
    private static final long serialVersionUID = 1322693251232626677L;
    private java.util.Map<String, String> latestVersions;
    private String legacyMode, latestDataDragon, store, defaultLocale, version, CDN, CSSVersion;
    private int maxProfileIconId;
    private Platform platform;

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
        final Realm other = (Realm)obj;
        if(CDN == null) {
            if(other.CDN != null) {
                return false;
            }
        } else if(!CDN.equals(other.CDN)) {
            return false;
        }
        if(CSSVersion == null) {
            if(other.CSSVersion != null) {
                return false;
            }
        } else if(!CSSVersion.equals(other.CSSVersion)) {
            return false;
        }
        if(defaultLocale == null) {
            if(other.defaultLocale != null) {
                return false;
            }
        } else if(!defaultLocale.equals(other.defaultLocale)) {
            return false;
        }
        if(latestDataDragon == null) {
            if(other.latestDataDragon != null) {
                return false;
            }
        } else if(!latestDataDragon.equals(other.latestDataDragon)) {
            return false;
        }
        if(latestVersions == null) {
            if(other.latestVersions != null) {
                return false;
            }
        } else if(!latestVersions.equals(other.latestVersions)) {
            return false;
        }
        if(legacyMode == null) {
            if(other.legacyMode != null) {
                return false;
            }
        } else if(!legacyMode.equals(other.legacyMode)) {
            return false;
        }
        if(maxProfileIconId != other.maxProfileIconId) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(store == null) {
            if(other.store != null) {
                return false;
            }
        } else if(!store.equals(other.store)) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    /**
     * @return the cDN
     */
    public String getCDN() {
        return CDN;
    }

    /**
     * @return the cSSVersion
     */
    public String getCSSVersion() {
        return CSSVersion;
    }

    /**
     * @return the defaultLocale
     */
    public String getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * @return the latestDataDragon
     */
    public String getLatestDataDragon() {
        return latestDataDragon;
    }

    /**
     * @return the latestVersions
     */
    public java.util.Map<String, String> getLatestVersions() {
        return latestVersions;
    }

    /**
     * @return the legacyMode
     */
    public String getLegacyMode() {
        return legacyMode;
    }

    /**
     * @return the maxProfileIconId
     */
    public int getMaxProfileIconId() {
        return maxProfileIconId;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the store
     */
    public String getStore() {
        return store;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (CDN == null ? 0 : CDN.hashCode());
        result = prime * result + (CSSVersion == null ? 0 : CSSVersion.hashCode());
        result = prime * result + (defaultLocale == null ? 0 : defaultLocale.hashCode());
        result = prime * result + (latestDataDragon == null ? 0 : latestDataDragon.hashCode());
        result = prime * result + (latestVersions == null ? 0 : latestVersions.hashCode());
        result = prime * result + (legacyMode == null ? 0 : legacyMode.hashCode());
        result = prime * result + maxProfileIconId;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (store == null ? 0 : store.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param CDN
     *        the CDN to set
     */
    public void setCDN(final String CDN) {
        this.CDN = CDN;
    }

    /**
     * @param CSSVersion
     *        the CSSVersion to set
     */
    public void setCSSVersion(final String CSSVersion) {
        this.CSSVersion = CSSVersion;
    }

    /**
     * @param defaultLocale
     *        the defaultLocale to set
     */
    public void setDefaultLocale(final String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    /**
     * @param latestDataDragon
     *        the latestDataDragon to set
     */
    public void setLatestDataDragon(final String latestDataDragon) {
        this.latestDataDragon = latestDataDragon;
    }

    /**
     * @param latestVersions
     *        the latestVersions to set
     */
    public void setLatestVersions(final java.util.Map<String, String> latestVersions) {
        this.latestVersions = latestVersions;
    }

    /**
     * @param legacyMode
     *        the legacyMode to set
     */
    public void setLegacyMode(final String legacyMode) {
        this.legacyMode = legacyMode;
    }

    /**
     * @param maxProfileIconId
     *        the maxProfileIconId to set
     */
    public void setMaxProfileIconId(final int maxProfileIconId) {
        this.maxProfileIconId = maxProfileIconId;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param store
     *        the store to set
     */
    public void setStore(final String store) {
        this.store = store;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
