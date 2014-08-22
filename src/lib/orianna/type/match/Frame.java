package lib.orianna.type.match;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Frame implements Serializable {
    private static final long serialVersionUID = 8641966743667411629L;
    public final List<Event> events;
    public final Map<Participant, ParticipantFrame> participantFrames;
    public final Long timestamp;

    public Frame(final List<Event> events, final Map<Participant, ParticipantFrame> participantFrames, final Long timestamp) {
        this.events = events;
        this.participantFrames = participantFrames;
        this.timestamp = timestamp;
    }

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (events == null ? 0 : events.hashCode());
        result = prime * result + (participantFrames == null ? 0 : participantFrames.hashCode());
        result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Frame [timestamp=" + timestamp + "]";
    }
}
