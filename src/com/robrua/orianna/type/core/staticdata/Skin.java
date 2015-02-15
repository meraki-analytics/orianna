package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class Skin extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Skin> {
    private static final long serialVersionUID = -3789160257893823965L;

    /**
     * @param data
     *            the underlying dto
     */
    public Skin(final com.robrua.orianna.type.dto.staticdata.Skin data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Skin.class);
    }

    /**
     * ID
     *
     * @return id
     */
    public int getID() {
        return super.getInteger(data.getId());
    }

    /**
     * Name
     *
     * @return name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * Num
     *
     * @return num
     */
    public int getNum() {
        return super.getInteger(data.getNum());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Skin (" + getName() + ")";
    }
}
