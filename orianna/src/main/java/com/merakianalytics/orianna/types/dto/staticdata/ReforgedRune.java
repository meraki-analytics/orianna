package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class ReforgedRune extends DataObject {
    private static final long serialVersionUID = 968057662476737104L;
    private int id, pathId, slot;
    private String key, name, shortDesc, longDesc, icon, platform, version, locale;

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
        final ReforgedRune other = (ReforgedRune)obj;
        if(icon == null) {
            if(other.icon != null) {
                return false;
            }
        } else if(!icon.equals(other.icon)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        } else if(!key.equals(other.key)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
            return false;
        }
        if(longDesc == null) {
            if(other.longDesc != null) {
                return false;
            }
        } else if(!longDesc.equals(other.longDesc)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(pathId != other.pathId) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(shortDesc == null) {
            if(other.shortDesc != null) {
                return false;
            }
        } else if(!shortDesc.equals(other.shortDesc)) {
            return false;
        }
        if(slot != other.slot) {
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
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the longDesc
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the pathId
     */
    public int getPathId() {
        return pathId;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the shortDesc
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * @return the slot
     */
    public int getSlot() {
        return slot;
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
        result = prime * result + (icon == null ? 0 : icon.hashCode());
        result = prime * result + id;
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (longDesc == null ? 0 : longDesc.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + pathId;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (shortDesc == null ? 0 : shortDesc.hashCode());
        result = prime * result + slot;
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param icon
     *        the icon to set
     */
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param key
     *        the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param longDesc
     *        the longDesc to set
     */
    public void setLongDesc(final String longDesc) {
        this.longDesc = longDesc;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param pathId
     *        the pathId to set
     */
    public void setPathId(final int pathId) {
        this.pathId = pathId;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param shortDesc
     *        the shortDesc to set
     */
    public void setShortDesc(final String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * @param slot
     *        the slot to set
     */
    public void setSlot(final int slot) {
        this.slot = slot;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
