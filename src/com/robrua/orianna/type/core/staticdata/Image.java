package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class Image extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Image> {
    private static final long serialVersionUID = 5892594153568140724L;

    /**
     * @param data
     *            the underlying dto
     */
    public Image(final com.robrua.orianna.type.dto.staticdata.Image data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Image.class);
    }

    /**
     * Full
     *
     * @return full
     */
    public String getFull() {
        return super.getString(data.getFull());
    }

    /**
     * Group
     *
     * @return group
     */
    public String getGroup() {
        return super.getString(data.getGroup());
    }

    /**
     * H
     *
     * @return h
     */
    public int getH() {
        return super.getInteger(data.getH());
    }

    /**
     * Sprite
     *
     * @return sprite
     */
    public String getSprite() {
        return super.getString(data.getSprite());
    }

    /**
     * W
     *
     * @return w
     */
    public int getW() {
        return super.getInteger(data.getW());
    }

    /**
     * X
     *
     * @return x
     */
    public int getX() {
        return super.getInteger(data.getX());
    }

    /**
     * Y
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
        return "Image (" + getFull() + ")";
    }
}
