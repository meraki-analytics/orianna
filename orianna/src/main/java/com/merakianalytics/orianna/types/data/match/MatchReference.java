package com.merakianalytics.orianna.types.data.match;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.common.Lane;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Role;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.data.CoreData;

public class MatchReference extends CoreData {
    private static final long serialVersionUID = 2383230574341149698L;
    private int championId;
    private DateTime creationTime;
    private long id;
    private Lane lane;
    private Platform platform;
    private Queue queue;
    private Role role;
    private Season season;

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
        final MatchReference other = (MatchReference)obj;
        if(championId != other.championId) {
            return false;
        }
        if(creationTime == null) {
            if(other.creationTime != null) {
                return false;
            }
        } else if(!creationTime.equals(other.creationTime)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(lane != other.lane) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(queue != other.queue) {
            return false;
        }
        if(role != other.role) {
            return false;
        }
        if(season != other.season) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public int getChampionId() {
        return championId;
    }

    /**
     * @return the creationTime
     */
    public DateTime getCreationTime() {
        return creationTime;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the lane
     */
    public Lane getLane() {
        return lane;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @return the season
     */
    public Season getSeason() {
        return season;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + championId;
        result = prime * result + (creationTime == null ? 0 : creationTime.hashCode());
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (lane == null ? 0 : lane.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (role == null ? 0 : role.hashCode());
        result = prime * result + (season == null ? 0 : season.hashCode());
        return result;
    }

    /**
     * @param championId
     *        the championId to set
     */
    public void setChampionId(final int championId) {
        this.championId = championId;
    }

    /**
     * @param creationTime
     *        the creationTime to set
     */
    public void setCreationTime(final DateTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @param lane
     *        the lane to set
     */
    public void setLane(final Lane lane) {
        this.lane = lane;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param queue
     *        the queue to set
     */
    public void setQueue(final Queue queue) {
        this.queue = queue;
    }

    /**
     * @param role
     *        the role to set
     */
    public void setRole(final Role role) {
        this.role = role;
    }

    /**
     * @param season
     *        the season to set
     */
    public void setSeason(final Season season) {
        this.season = season;
    }
}
