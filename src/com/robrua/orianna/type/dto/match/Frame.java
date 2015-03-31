package com.robrua.orianna.type.dto.match;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "frame")
public class Frame extends OriannaDto {
    private static final long serialVersionUID = 4239036611650793396L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, ParticipantFrame> participantFrames;

    private Long timestamp;

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
        if(!(obj instanceof Frame)) {
            return false;
        }
        final Frame other = (Frame)obj;
        if(events == null) {
            if(other.events != null) {
                return false;
            }
        }
        else if(!events.equals(other.events)) {
            return false;
        }
        if(participantFrames == null) {
            if(other.participantFrames != null) {
                return false;
            }
        }
        else if(!participantFrames.equals(other.participantFrames)) {
            return false;
        }
        if(timestamp == null) {
            if(other.timestamp != null) {
                return false;
            }
        }
        else if(!timestamp.equals(other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @return the participantFrames
     */
    public Map<String, ParticipantFrame> getParticipantFrames() {
        return participantFrames;
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (events == null ? 0 : events.hashCode());
        result = prime * result + (participantFrames == null ? 0 : participantFrames.hashCode());
        result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
        return result;
    }

    /**
     * @param events
     *            the events to set
     */
    public void setEvents(final List<Event> events) {
        this.events = events;
    }

    /**
     * @param participantFrames
     *            the participantFrames to set
     */
    public void setParticipantFrames(final Map<String, ParticipantFrame> participantFrames) {
        this.participantFrames = participantFrames;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Frame [events=" + events + ", participantFrames=" + participantFrames + ", timestamp=" + timestamp + "]";
    }
}
