package com.robrua.orianna.type.core.match;

import com.robrua.orianna.type.core.OriannaObject;

public class Position extends OriannaObject<com.robrua.orianna.type.dto.match.Position> {
    private static final long serialVersionUID = 6689055503592657967L;

    /**
     * @param data
     *            the underlying dto
     */
    public Position(final com.robrua.orianna.type.dto.match.Position data) {
        super(data, com.robrua.orianna.type.dto.match.Position.class);
    }

    /**
     * x
     *
     * @return x
     */
    public int getX() {
        return super.getInteger(data.getX());
    }

    /**
     * y
     *
     * @return y
     */
    public int getY() {
        return super.getInteger(data.getY());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Position (" + getX() + ", " + getY() + ")";
    }
}
