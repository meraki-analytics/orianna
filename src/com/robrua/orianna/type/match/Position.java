package com.robrua.orianna.type.match;

import java.io.Serializable;

public class Position implements Serializable {
    private static final long serialVersionUID = -4372381323980445228L;
    public final Integer x, y;

    public Position(final Integer x, final Integer y) {
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
        if(!(obj instanceof Position)) {
            return false;
        }
        final Position other = (Position)obj;
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
        result = prime * result + (x == null ? 0 : x.hashCode());
        result = prime * result + (y == null ? 0 : y.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
}
