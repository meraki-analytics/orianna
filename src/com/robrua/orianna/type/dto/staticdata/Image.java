package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "image")
public class Image extends OriannaDto {
    private static final long serialVersionUID = -6019788102590496443L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private String full, sprite;

    @Column(name = "grp")
    private String group;

    private Integer h, w, x, y;

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
        if(!(obj instanceof Image)) {
            return false;
        }
        final Image other = (Image)obj;
        if(full == null) {
            if(other.full != null) {
                return false;
            }
        }
        else if(!full.equals(other.full)) {
            return false;
        }
        if(group == null) {
            if(other.group != null) {
                return false;
            }
        }
        else if(!group.equals(other.group)) {
            return false;
        }
        if(h == null) {
            if(other.h != null) {
                return false;
            }
        }
        else if(!h.equals(other.h)) {
            return false;
        }
        if(sprite == null) {
            if(other.sprite != null) {
                return false;
            }
        }
        else if(!sprite.equals(other.sprite)) {
            return false;
        }
        if(w == null) {
            if(other.w != null) {
                return false;
            }
        }
        else if(!w.equals(other.w)) {
            return false;
        }
        if(x == null) {
            if(other.x != null) {
                return false;
            }
        }
        else if(!x.equals(other.x)) {
            return false;
        }
        if(y == null) {
            if(other.y != null) {
                return false;
            }
        }
        else if(!y.equals(other.y)) {
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
    public Integer getH() {
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
    public Integer getW() {
        return w;
    }

    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * @return the y
     */
    public Integer getY() {
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
        result = prime * result + (h == null ? 0 : h.hashCode());
        result = prime * result + (sprite == null ? 0 : sprite.hashCode());
        result = prime * result + (w == null ? 0 : w.hashCode());
        result = prime * result + (x == null ? 0 : x.hashCode());
        result = prime * result + (y == null ? 0 : y.hashCode());
        return result;
    }

    /**
     * @param full
     *            the full to set
     */
    public void setFull(final String full) {
        this.full = full;
    }

    /**
     * @param group
     *            the group to set
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * @param h
     *            the h to set
     */
    public void setH(final Integer h) {
        this.h = h;
    }

    /**
     * @param sprite
     *            the sprite to set
     */
    public void setSprite(final String sprite) {
        this.sprite = sprite;
    }

    /**
     * @param w
     *            the w to set
     */
    public void setW(final Integer w) {
        this.w = w;
    }

    /**
     * @param x
     *            the x to set
     */
    public void setX(final Integer x) {
        this.x = x;
    }

    /**
     * @param y
     *            the y to set
     */
    public void setY(final Integer y) {
        this.y = y;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Image [full=" + full + ", group=" + group + ", sprite=" + sprite + ", h=" + h + ", w=" + w + ", x=" + x + ", y=" + y + "]";
    }
}
