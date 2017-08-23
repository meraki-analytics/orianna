package com.merakianalytics.orianna.types.dto.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MapDetails extends DataObject {
    private static final long serialVersionUID = -1421402363346671183L;
    private Image image;
    private long mapId;
    private String mapName, platform, locale, version;
    private List<Long> unpurchasableItemList;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final MapDetails other = (MapDetails)obj;
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        } else if(!image.equals(other.image)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
            return false;
        }
        if(mapId != other.mapId) {
            return false;
        }
        if(mapName == null) {
            if(other.mapName != null) {
                return false;
            }
        } else if(!mapName.equals(other.mapName)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(unpurchasableItemList == null) {
            if(other.unpurchasableItemList != null) {
                return false;
            }
        } else if(!unpurchasableItemList.equals(other.unpurchasableItemList)) {
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
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the mapId
     */
    public long getMapId() {
        return mapId;
    }

    /**
     * @return the mapName
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the unpurchasableItemList
     */
    public List<Long> getUnpurchasableItemList() {
        return unpurchasableItemList;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (int)(mapId ^ mapId >>> 32);
        result = prime * result + (mapName == null ? 0 : mapName.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (unpurchasableItemList == null ? 0 : unpurchasableItemList.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param image
     *        the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param mapId
     *        the mapId to set
     */
    public void setMapId(final long mapId) {
        this.mapId = mapId;
    }

    /**
     * @param mapName
     *        the mapName to set
     */
    public void setMapName(final String mapName) {
        this.mapName = mapName;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param unpurchasableItemList
     *        the unpurchasableItemList to set
     */
    public void setUnpurchasableItemList(final List<Long> unpurchasableItemList) {
        this.unpurchasableItemList = unpurchasableItemList;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
