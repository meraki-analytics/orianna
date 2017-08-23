package com.merakianalytics.orianna.types.data.match;

import com.merakianalytics.orianna.types.data.CoreData;

public class Position extends CoreData {
    private static final long serialVersionUID = 5993854891107334985L;
    private int x, y;

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
        final Position other = (Position)obj;
        if(x != other.x) {
            return false;
        }
        if(y != other.y) {
            return false;
        }
        return true;
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
        result = prime * result + x;
        result = prime * result + y;
        return result;
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
