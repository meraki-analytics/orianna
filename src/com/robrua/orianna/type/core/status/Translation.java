package com.robrua.orianna.type.core.status;

import com.robrua.orianna.type.core.OriannaObject;

public class Translation extends OriannaObject<com.robrua.orianna.type.dto.status.Translation> {
    private static final long serialVersionUID = -8003753177299140690L;

    /**
     * @param data
     *            the underlying dto
     */
    public Translation(final com.robrua.orianna.type.dto.status.Translation data) {
        super(data, com.robrua.orianna.type.dto.status.Translation.class);
    }

    /**
     * The content
     *
     * @return the content
     */
    public String getContent() {
        return super.getString(data.getContent());
    }

    /**
     * The locale
     *
     * @return the locale
     */
    public String getLocale() {
        return super.getString(data.getLocale());
    }

    /**
     * The updated at value
     *
     * @return the updated at value
     */
    public String getUpdatedAt() {
        return super.getString(data.getUpdated_at());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Translation (" + getContent() + ")";
    }
}
