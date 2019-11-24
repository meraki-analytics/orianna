package com.merakianalytics.orianna.types.data.status;

import com.merakianalytics.orianna.types.data.CoreData;

public class Translation extends CoreData {
    private static final long serialVersionUID = -2766073434420174632L;
    private String locale, content, heading;

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
        if(heading == null) {
            if(other.heading != null) {
                return false;
            }
        } else if(!heading.equals(other.heading)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
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
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (content == null ? 0 : content.hashCode());
        result = prime * result + (heading == null ? 0 : heading.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
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
     * @param heading
     *        the heading to set
     */
    public void setHeading(final String heading) {
        this.heading = heading;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }
}
