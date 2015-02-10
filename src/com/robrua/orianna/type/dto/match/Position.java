package com.robrua.orianna.type.dto.match;

import com.robrua.orianna.type.dto.OriannaDto;

public class Position extends OriannaDto {
    private static final long serialVersionUID = 6283012235679557217L;
    private Integer x, y;

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
        result = prime * result + (x == null ? 0 : x.hashCode());
        result = prime * result + (y == null ? 0 : y.hashCode());
        return result;
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
        return "Position [x=" + x + ", y=" + y + "]";
    }
}
