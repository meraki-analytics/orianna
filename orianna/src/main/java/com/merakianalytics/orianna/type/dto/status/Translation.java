package com.merakianalytics.orianna.type.dto.status;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Translation extends DataObject {
    private static final long serialVersionUID = -3556616770908178015L;
    private String locale, content, updated_at;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Translation other = (Translation)obj;
        if(content == null) {
            if(other.content != null) {
                return false;
            }
        } else if(!content.equals(other.content)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
            return false;
        }
        if(updated_at == null) {
            if(other.updated_at != null) {
                return false;
            }
        } else if(!updated_at.equals(other.updated_at)) {
            return false;
        }
        return true;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the updated_at
     */
    public String getUpdated_at() {
        return updated_at;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (content == null ? 0 : content.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (updated_at == null ? 0 : updated_at.hashCode());
        return result;
    }

    /**
     * @param content
     *        the content to set
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param updated_at
     *        the updated_at to set
     */
    public void setUpdated_at(final String updated_at) {
        this.updated_at = updated_at;
    }
}
