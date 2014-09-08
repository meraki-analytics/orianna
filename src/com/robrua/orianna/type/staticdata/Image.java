package com.robrua.orianna.type.staticdata;

import java.io.Serializable;

public class Image implements Serializable {
    private static final long serialVersionUID = -257806374483414051L;
    public final String full, group, sprite;
    public final Integer h, w, x, y;

    public Image(final String full, final String group, final String sprite, final Integer h, final Integer w, final Integer x, final Integer y) {
        this.full = full;
        this.group = group;
        this.sprite = sprite;
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;
    }

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

    @Override
    public String toString() {
        return full;
    }
}
