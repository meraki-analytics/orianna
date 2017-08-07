package com.merakianalytics.orianna.types.data.match;

import java.util.Map;

import org.joda.time.Duration;

import com.merakianalytics.orianna.types.data.CoreData;

public class Frame extends CoreData.ListProxy<Event> {
    private static final long serialVersionUID = -2557460148105527544L;
    private Map<Integer, ParticipantFrame> participantFrames;
    private Duration timestamp;

    public Frame() {
        super();
    }

    public Frame(final int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(!super.equals(obj)) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Frame other = (Frame)obj;
        if(participantFrames == null) {
            if(other.participantFrames != null) {
                return false;
            }
        } else if(!participantFrames.equals(other.participantFrames)) {
            return false;
        }
        if(timestamp == null) {
            if(other.timestamp != null) {
                return false;
            }
        } else if(!timestamp.equals(other.timestamp)) {
            return false;
        }
        return true;
    }

    /**
     * @return the participantFrames
     */
    public Map<Integer, ParticipantFrame> getParticipantFrames() {
        return participantFrames;
    }

    /**
     * @return the timestamp
     */
    public Duration getTimestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (participantFrames == null ? 0 : participantFrames.hashCode());
        result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
        return result;
    }

    /**
     * @param participantFrames
     *        the participantFrames to set
     */
    public void setParticipantFrames(final Map<Integer, ParticipantFrame> participantFrames) {
        this.participantFrames = participantFrames;
    }

    /**
     * @param timestamp
     *        the timestamp to set
     */
    public void setTimestamp(final Duration timestamp) {
        this.timestamp = timestamp;
    }
}
