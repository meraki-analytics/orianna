package com.robrua.orianna.type.dto.status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "translation")
public class Translation extends OriannaDto {
    private static final long serialVersionUID = -3189944613741543831L;
    private String content, locale, updated_at;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

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
        if(!(obj instanceof Translation)) {
            return false;
        }
        final Translation other = (Translation)obj;
        if(content == null) {
            if(other.content != null) {
                return false;
            }
        }
        else if(!content.equals(other.content)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        }
        else if(!locale.equals(other.locale)) {
            return false;
        }
        if(updated_at == null) {
            if(other.updated_at != null) {
                return false;
            }
        }
        else if(!updated_at.equals(other.updated_at)) {
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

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
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
     *            the content to set
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param updated_at
     *            the updated_at to set
     */
    public void setUpdated_at(final String updated_at) {
        this.updated_at = updated_at;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Translation [content=" + content + ", locale=" + locale + ", updated_at=" + updated_at + "]";
    }
}
