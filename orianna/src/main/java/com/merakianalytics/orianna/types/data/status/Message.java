package com.merakianalytics.orianna.types.data.status;

import java.util.Map;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.data.CoreData;

public class Message extends CoreData {
    private static final long serialVersionUID = -1970222775356690224L;
    private DateTime created, updated;
    private String severity, author, content, id;
    private Map<String, Translation> translations;

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
        if(created == null) {
            if(other.created != null) {
                return false;
            }
        } else if(!created.equals(other.created)) {
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
     * @return the created
     */
    public DateTime getCreated() {
        return created;
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
    public Map<String, Translation> getTranslations() {
        return translations;
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
        result = prime * result + (author == null ? 0 : author.hashCode());
        result = prime * result + (content == null ? 0 : content.hashCode());
        result = prime * result + (created == null ? 0 : created.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (severity == null ? 0 : severity.hashCode());
        result = prime * result + (translations == null ? 0 : translations.hashCode());
        result = prime * result + (updated == null ? 0 : updated.hashCode());
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
     * @param created
     *        the created to set
     */
    public void setCreated(final DateTime created) {
        this.created = created;
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
    public void setTranslations(final Map<String, Translation> translations) {
        this.translations = translations;
    }

    /**
     * @param updated
     *        the updated to set
     */
    public void setUpdated(final DateTime updated) {
        this.updated = updated;
    }
}
