package lib.orianna.type.match;

import java.io.Serializable;
import java.util.List;

public class MatchTimeline implements Serializable {
    private static final long serialVersionUID = -2915742245908144161L;
    public final Long frameInterval;
    public final List<Frame> frames;

    public MatchTimeline(final Long frameInterval, final List<Frame> frames) {
        this.frameInterval = frameInterval;
        this.frames = frames;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MatchTimeline)) {
            return false;
        }
        final MatchTimeline other = (MatchTimeline)obj;
        if(frameInterval == null) {
            if(other.frameInterval != null) {
                return false;
            }
        }
        else if(!frameInterval.equals(other.frameInterval)) {
            return false;
        }
        if(frames == null) {
            if(other.frames != null) {
                return false;
            }
        }
        else if(!frames.equals(other.frames)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (frameInterval == null ? 0 : frameInterval.hashCode());
        result = prime * result + (frames == null ? 0 : frames.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MatchTimeline";
    }
}
