package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class Passive extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Passive> {
    private static final long serialVersionUID = 2132773068206285456L;
    private Image image;

    /**
     * @param data
     *            the underlying dto
     */
    public Passive(final com.robrua.orianna.type.dto.staticdata.Passive data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Passive.class);
    }

    /**
     * Description
     *
     * @return description
     */
    public String getDescription() {
        return super.getString(data.getDescription());
    }

    /**
     * Image
     *
     * @return image
     */
    public Image getImage() {
        if(image == null) {
            image = new Image(data.getImage());
        }

        return image;
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
     * Sanitized description
     *
     * @return sanitized description
     */
    public String getSanitizedDescription() {
        return super.getString(data.getSanitizedDescription());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getName();
    }
}
