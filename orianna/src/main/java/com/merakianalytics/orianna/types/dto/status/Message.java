package com.merakianalytics.orianna.types.dto.status;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Message extends DataObject {
    private static final long serialVersionUID = 1419856168249235794L;
    private String severity, author, created_at, updated_at, content, id;
    private List<Translation> translations;

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
        final Message other = (Message)obj;
        if(author == null) {
            if(other.author != null) {
                return false;
            }
        } else if(!author.equals(other.author)) {
            return false;
        }
        if(content == null) {
            if(other.content != null) {
                return false;
            }
        } else if(!content.equals(other.content)) {
            return false;
        }
        if(created_at == null) {
            if(other.created_at != null) {
                return false;
            }
        } else if(!created_at.equals(other.created_at)) {
            return false;
        }
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        } else if(!id.equals(other.id)) {
            return false;
        }
        if(severity == null) {
            if(other.severity != null) {
                return false;
            }
        } else if(!severity.equals(other.severity)) {
            return false;
        }
        if(translations == null) {
            if(other.translations != null) {
                return false;
            }
        } else if(!translations.equals(other.translations)) {
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
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the created_at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * @return the translations
     */
    public List<Translation> getTranslations() {
        return translations;
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
        result = prime * result + (author == null ? 0 : author.hashCode());
        result = prime * result + (content == null ? 0 : content.hashCode());
        result = prime * result + (created_at == null ? 0 : created_at.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (severity == null ? 0 : severity.hashCode());
        result = prime * result + (translations == null ? 0 : translations.hashCode());
        result = prime * result + (updated_at == null ? 0 : updated_at.hashCode());
        return result;
    }

    /**
     * @param author
     *        the author to set
     */
    public void setAuthor(final String author) {
        this.author = author;
    }

    /**
     * @param content
     *        the content to set
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * @param created_at
     *        the created_at to set
     */
    public void setCreated_at(final String created_at) {
        this.created_at = created_at;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @param severity
     *        the severity to set
     */
    public void setSeverity(final String severity) {
        this.severity = severity;
    }

    /**
     * @param translations
     *        the translations to set
     */
    public void setTranslations(final List<Translation> translations) {
        this.translations = translations;
    }

    /**
     * @param updated_at
     *        the updated_at to set
     */
    public void setUpdated_at(final String updated_at) {
        this.updated_at = updated_at;
    }
}
