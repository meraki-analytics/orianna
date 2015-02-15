package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class Gold extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Gold> {
    private static final long serialVersionUID = 7318464693517961593L;

    /**
     * @param data
     *            the underlying dto
     */
    public Gold(final com.robrua.orianna.type.dto.staticdata.Gold data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Gold.class);
    }

    /**
     * Base
     *
     * @return base
     */
    public int getBase() {
        return super.getInteger(data.getBase());
    }

    /**
     * Purchasable
     *
     * @return purchasable
     */
    public boolean getPurchasable() {
        return super.getBoolean(data.getPurchasable());
    }

    /**
     * Sell
     *
     * @return sell
     */
    public int getSell() {
        return super.getInteger(data.getSell());
    }

    /**
     * Total
     *
     * @return total
     */
    public int getTotal() {
        return super.getInteger(data.getTotal());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Gold (" + getTotal() + ")";
    }
}
