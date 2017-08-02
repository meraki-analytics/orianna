package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Image extends DataObject {
    private static final long serialVersionUID = 4913831808071030898L;
    private String full, group, sprite;
    private int h, w, y, x;

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
        if(h != other.h) {
            return false;
        }
        if(sprite == null) {
            if(other.sprite != null) {
                return false;
            }
        } else if(!sprite.equals(other.sprite)) {
            return false;
        }
        if(w != other.w) {
            return false;
        }
        if(x != other.x) {
            return false;
        }
        if(y != other.y) {
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
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * @return the sprite
     */
    public String getSprite() {
        return sprite;
    }

    /**
     * @return the w
     */
    public int getW() {
        return w;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (full == null ? 0 : full.hashCode());
        result = prime * result + (group == null ? 0 : group.hashCode());
        result = prime * result + h;
        result = prime * result + (sprite == null ? 0 : sprite.hashCode());
        result = prime * result + w;
        result = prime * result + x;
        result = prime * result + y;
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
     * @param h
     *        the h to set
     */
    public void setH(final int h) {
        this.h = h;
    }

    /**
     * @param sprite
     *        the sprite to set
     */
    public void setSprite(final String sprite) {
        this.sprite = sprite;
    }

    /**
     * @param w
     *        the w to set
     */
    public void setW(final int w) {
        this.w = w;
    }

    /**
     * @param x
     *        the x to set
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * @param y
     *        the y to set
     */
    public void setY(final int y) {
        this.y = y;
    }
}
