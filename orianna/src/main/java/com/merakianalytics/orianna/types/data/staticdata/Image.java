package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class Image extends CoreData {
    private static final long serialVersionUID = -1543791679428823570L;
    private String full, group, version;
    private Sprite sprite;

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
        final Image other = (Image)obj;
        if(full == null) {
            if(other.full != null) {
                return false;
            }
        } else if(!full.equals(other.full)) {
            return false;
        }
        if(group == null) {
            if(other.group != null) {
                return false;
            }
        } else if(!group.equals(other.group)) {
            return false;
        }
        if(sprite == null) {
            if(other.sprite != null) {
                return false;
            }
        } else if(!sprite.equals(other.sprite)) {
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
     * @return the full
     */
    public String getFull() {
        return full;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
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
        result = prime * result + (full == null ? 0 : full.hashCode());
        result = prime * result + (group == null ? 0 : group.hashCode());
        result = prime * result + (sprite == null ? 0 : sprite.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param full
     *        the full to set
     */
    public void setFull(final String full) {
        this.full = full;
    }

    /**
     * @param group
     *        the group to set
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * @param sprite
     *        the sprite to set
     */
    public void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
