package com.merakianalytics.orianna.types.dto.match;

import java.util.List;
import java.util.Map;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MatchFrame extends DataObject {
    private static final long serialVersionUID = -4247119702676575684L;
    private List<MatchEvent> events;
    private Map<String, MatchParticipantFrame> participantFrames;
    private long timestamp;

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
        final MatchFrame other = (MatchFrame)obj;
        if(events == null) {
            if(other.events != null) {
                return false;
            }
        } else if(!events.equals(other.events)) {
            return false;
        }
        if(participantFrames == null) {
            if(other.participantFrames != null) {
                return false;
            }
        } else if(!participantFrames.equals(other.participantFrames)) {
            return false;
        }
        if(timestamp != other.timestamp) {
            return false;
        }
        return true;
    }

    /**
     * @return the events
     */
    public List<MatchEvent> getEvents() {
        return events;
    }

    /**
     * @return the participantFrames
     */
    public Map<String, MatchParticipantFrame> getParticipantFrames() {
        return participantFrames;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
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
        result = prime * result + (int)(timestamp ^ timestamp >>> 32);
        return result;
    }

    /**
     * @param events
     *        the events to set
     */
    public void setEvents(final List<MatchEvent> events) {
        this.events = events;
    }

    /**
     * @param participantFrames
     *        the participantFrames to set
     */
    public void setParticipantFrames(final Map<String, MatchParticipantFrame> participantFrames) {
        this.participantFrames = participantFrames;
    }

    /**
     * @param timestamp
     *        the timestamp to set
     */
    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }
}
