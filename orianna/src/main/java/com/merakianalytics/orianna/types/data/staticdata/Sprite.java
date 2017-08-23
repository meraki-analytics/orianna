package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class Sprite extends CoreData {
    private static final long serialVersionUID = -1150310890474962226L;
    private String full, version;
    private int x, y, width, height;

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
        final Sprite other = (Sprite)obj;
        if(full == null) {
            if(other.full != null) {
                return false;
            }
        } else if(!full.equals(other.full)) {
            return false;
        }
        if(height != other.height) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
            return false;
        }
        if(width != other.width) {
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
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (full == null ? 0 : full.hashCode());
        result = prime * result + height;
        result = prime * result + (version == null ? 0 : version.hashCode());
        result = prime * result + width;
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
     * @param height
     *        the height to set
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * @param width
     *        the width to set
     */
    public void setWidth(final int width) {
        this.width = width;
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
