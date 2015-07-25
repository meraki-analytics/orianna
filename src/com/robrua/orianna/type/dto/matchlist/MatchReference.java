package com.robrua.orianna.type.dto.matchlist;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "matchreference")
public class MatchReference extends OriannaDto {
    private static final long serialVersionUID = -3579776398026544389L;
    private Long champion, timestamp;
    private String lane, platformId, queue, role, season;

    @Id
    private Long matchId;

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
        if(!(obj instanceof MatchReference)) {
            return false;
        }
        final MatchReference other = (MatchReference)obj;
        if(champion == null) {
            if(other.champion != null) {
                return false;
            }
        }
        else if(!champion.equals(other.champion)) {
            return false;
        }
        if(lane == null) {
            if(other.lane != null) {
                return false;
            }
        }
        else if(!lane.equals(other.lane)) {
            return false;
        }
        if(matchId == null) {
            if(other.matchId != null) {
                return false;
            }
        }
        else if(!matchId.equals(other.matchId)) {
            return false;
        }
        if(platformId == null) {
            if(other.platformId != null) {
                return false;
            }
        }
        else if(!platformId.equals(other.platformId)) {
            return false;
        }
        if(queue == null) {
            if(other.queue != null) {
                return false;
            }
        }
        else if(!queue.equals(other.queue)) {
            return false;
        }
        if(role == null) {
            if(other.role != null) {
                return false;
            }
        }
        else if(!role.equals(other.role)) {
            return false;
        }
        if(season == null) {
            if(other.season != null) {
                return false;
            }
        }
        else if(!season.equals(other.season)) {
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

    /**
     * @return the champion
     */
    public Long getChampion() {
        return champion;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "matchId";
        }
        return null;
    }

    /**
     * @return the lane
     */
    public String getLane() {
        return lane;
    }

    /**
     * @return the matchId
     */
    public Long getMatchId() {
        return matchId;
    }

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the season
     */
    public String getSeason() {
        return season;
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
        result = prime * result + (champion == null ? 0 : champion.hashCode());
        result = prime * result + (lane == null ? 0 : lane.hashCode());
        result = prime * result + (matchId == null ? 0 : matchId.hashCode());
        result = prime * result + (platformId == null ? 0 : platformId.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (role == null ? 0 : role.hashCode());
        result = prime * result + (season == null ? 0 : season.hashCode());
        result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
        return result;
    }

    /**
     * @param champion
     *            the champion to set
     */
    public void setChampion(final Long champion) {
        this.champion = champion;
    }

    /**
     * @param lane
     *            the lane to set
     */
    public void setLane(final String lane) {
        this.lane = lane;
    }

    /**
     * @param matchId
     *            the matchId to set
     */
    public void setMatchId(final Long matchId) {
        this.matchId = matchId;
    }

    /**
     * @param platformId
     *            the platformId to set
     */
    public void setPlatformId(final String platformId) {
        this.platformId = platformId;
    }

    /**
     * @param queue
     *            the queue to set
     */
    public void setQueue(final String queue) {
        this.queue = queue;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * @param season
     *            the season to set
     */
    public void setSeason(final String season) {
        this.season = season;
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
        return "MatchReference [champion=" + champion + ", timestamp=" + timestamp + ", lane=" + lane + ", platformId=" + platformId + ", queue=" + queue
                + ", role=" + role + ", season=" + season + ", matchId=" + matchId + "]";
    }

}
