package com.merakianalytics.orianna.types.data.status;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.data.CoreData;

public class Translation extends CoreData {
    private static final long serialVersionUID = 5006746492830237615L;
    private String locale, content;
    private DateTime updated;

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
        if(updated == null) {
            if(other.updated != null) {
                return false;
            }
        } else if(!updated.equals(other.updated)) {
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
     * @return the updated
     */
    public DateTime getUpdated() {
        return updated;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (content == null ? 0 : content.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (updated == null ? 0 : updated.hashCode());
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
     * @param updated
     *        the updated to set
     */
    public void setUpdated(final DateTime updated) {
        this.updated = updated;
    }
}
