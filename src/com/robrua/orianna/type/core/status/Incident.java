package com.robrua.orianna.type.core.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class Incident extends OriannaObject<com.robrua.orianna.type.dto.status.Incident> {
    private static final long serialVersionUID = 6619018597438629232L;
    private List<Message> updates;

    /**
     * @param data
     *            the underlying dto
     */
    public Incident(final com.robrua.orianna.type.dto.status.Incident data) {
        super(data, com.robrua.orianna.type.dto.status.Incident.class);
    }

    /**
     * If the incident is active
     *
     * @return whether the incident is active
     */
    public boolean getActive() {
        return super.getBoolean(data.getActive());
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
     * The updates
     *
     * @return the updates
     */
    public List<Message> getUpdates() {
        if(updates == null) {
            updates = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.status.Message message : data.getUpdates()) {
                updates.add(new Message(message));
            }
        }

        return Collections.unmodifiableList(updates);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Incident (" + getID() + ")";
    }
}
