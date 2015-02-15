package com.robrua.orianna.type.core.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class Message extends OriannaObject<com.robrua.orianna.type.dto.status.Message> {
    private static final long serialVersionUID = 330160592720735279L;
    private List<Translation> translations;

    /**
     * @param data
     *            the underlying dto
     */
    public Message(final com.robrua.orianna.type.dto.status.Message data) {
        super(data, com.robrua.orianna.type.dto.status.Message.class);
    }

    /**
     * The author
     *
     * @return the author
     */
    public String getAuthor() {
        return super.getString(data.getAuthor());
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
     * The created at value
     *
     * @return the created at value
     */
    public String getCreatedAt() {
        return super.getString(data.getCreated_at());
    }

    /**
     * The ID
     *
     * @return the ID
     */
    public long getID() {
        return super.getLong(data.getId());
    }

    /**
     * The severity
     *
     * @return the severity
     */
    public String getSeverity() {
        return super.getString(data.getSeverity());
    }

    /**
     * The translations
     *
     * @return the translations
     */
    public List<Translation> getTranslations() {
        if(translations == null) {
            translations = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.status.Translation trans : data.getTranslations()) {
                translations.add(new Translation(trans));
            }
        }

        return Collections.unmodifiableList(translations);
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
        return "Message (" + getContent() + ")";
    }
}
