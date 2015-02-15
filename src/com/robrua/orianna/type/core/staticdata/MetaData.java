package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class MetaData extends OriannaObject<com.robrua.orianna.type.dto.staticdata.MetaData> {
    private static final long serialVersionUID = -8657779790595292619L;

    /**
     * @param data
     *            the underlying dto
     */
    public MetaData(final com.robrua.orianna.type.dto.staticdata.MetaData data) {
        super(data, com.robrua.orianna.type.dto.staticdata.MetaData.class);
    }

    /**
     * Is rune
     *
     * @return is rune
     */
    public boolean getIsRune() {
        return super.getBoolean(data.getIsRune());
    }

    /**
     * Tier
     *
     * @return tier
     */
    public String getTier() {
        return super.getString(data.getTier());
    }

    /**
     * Type
     *
     * @return type
     */
    public String getType() {
        return super.getString(data.getType());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MetaData";
    }
}
