package com.merakianalytics.orianna.types.dto.match;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MatchTimeline extends DataObject {
    private static final long serialVersionUID = 5433246300212121306L;
    private long frameInterval, matchId;
    private List<MatchFrame> frames;
    private String platform;

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
        final MatchTimeline other = (MatchTimeline)obj;
        if(frameInterval != other.frameInterval) {
            return false;
        }
        if(frames == null) {
            if(other.frames != null) {
                return false;
            }
        } else if(!frames.equals(other.frames)) {
            return false;
        }
        if(matchId != other.matchId) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        return true;
    }

    /**
     * @return the frameInterval
     */
    public long getFrameInterval() {
        return frameInterval;
    }

    /**
     * @return the frames
     */
    public List<MatchFrame> getFrames() {
        return frames;
    }

    /**
     * @return the matchId
     */
    public long getMatchId() {
        return matchId;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(frameInterval ^ frameInterval >>> 32);
        result = prime * result + (frames == null ? 0 : frames.hashCode());
        result = prime * result + (int)(matchId ^ matchId >>> 32);
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    /**
     * @param frameInterval
     *        the frameInterval to set
     */
    public void setFrameInterval(final long frameInterval) {
        this.frameInterval = frameInterval;
    }

    /**
     * @param frames
     *        the frames to set
     */
    public void setFrames(final List<MatchFrame> frames) {
        this.frames = frames;
    }

    /**
     * @param matchId
     *        the matchId to set
     */
    public void setMatchId(final long matchId) {
        this.matchId = matchId;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
