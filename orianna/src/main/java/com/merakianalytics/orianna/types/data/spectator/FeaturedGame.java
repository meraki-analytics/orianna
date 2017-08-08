package com.merakianalytics.orianna.types.data.spectator;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.data.CoreData;

public class FeaturedGame extends CoreData {
    private static final long serialVersionUID = 590268111588592011L;
    private List<Integer> blueTeamBans, redTeamBans;
    private DateTime creationTime;
    private Duration duration;
    private long id;
    private Map map;
    private GameMode mode;
    private String observerEncryptionKey;
    private Platform platform;
    private List<Participant> players;
    private Queue queue;
    private GameType type;

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
        final FeaturedGame other = (FeaturedGame)obj;
        if(blueTeamBans == null) {
            if(other.blueTeamBans != null) {
                return false;
            }
        } else if(!blueTeamBans.equals(other.blueTeamBans)) {
            return false;
        }
        if(creationTime == null) {
            if(other.creationTime != null) {
                return false;
            }
        } else if(!creationTime.equals(other.creationTime)) {
            return false;
        }
        if(duration == null) {
            if(other.duration != null) {
                return false;
            }
        } else if(!duration.equals(other.duration)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(map != other.map) {
            return false;
        }
        if(mode != other.mode) {
            return false;
        }
        if(observerEncryptionKey == null) {
            if(other.observerEncryptionKey != null) {
                return false;
            }
        } else if(!observerEncryptionKey.equals(other.observerEncryptionKey)) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(players == null) {
            if(other.players != null) {
                return false;
            }
        } else if(!players.equals(other.players)) {
            return false;
        }
        if(queue != other.queue) {
            return false;
        }
        if(redTeamBans == null) {
            if(other.redTeamBans != null) {
                return false;
            }
        } else if(!redTeamBans.equals(other.redTeamBans)) {
            return false;
        }
        if(type != other.type) {
            return false;
        }
        return true;
    }

    /**
     * @return the blueTeamBans
     */
    public List<Integer> getBlueTeamBans() {
        return blueTeamBans;
    }

    /**
     * @return the creationTime
     */
    public DateTime getCreationTime() {
        return creationTime;
    }

    /**
     * @return the duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @return the mode
     */
    public GameMode getMode() {
        return mode;
    }

    /**
     * @return the observerEncryptionKey
     */
    public String getObserverEncryptionKey() {
        return observerEncryptionKey;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the players
     */
    public List<Participant> getPlayers() {
        return players;
    }

    /**
     * @return the queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * @return the redTeamBans
     */
    public List<Integer> getRedTeamBans() {
        return redTeamBans;
    }

    /**
     * @return the type
     */
    public GameType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (blueTeamBans == null ? 0 : blueTeamBans.hashCode());
        result = prime * result + (creationTime == null ? 0 : creationTime.hashCode());
        result = prime * result + (duration == null ? 0 : duration.hashCode());
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (map == null ? 0 : map.hashCode());
        result = prime * result + (mode == null ? 0 : mode.hashCode());
        result = prime * result + (observerEncryptionKey == null ? 0 : observerEncryptionKey.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (players == null ? 0 : players.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (redTeamBans == null ? 0 : redTeamBans.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @param blueTeamBans
     *        the blueTeamBans to set
     */
    public void setBlueTeamBans(final List<Integer> blueTeamBans) {
        this.blueTeamBans = blueTeamBans;
    }

    /**
     * @param creationTime
     *        the creationTime to set
     */
    public void setCreationTime(final DateTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @param duration
     *        the duration to set
     */
    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @param map
     *        the map to set
     */
    public void setMap(final Map map) {
        this.map = map;
    }

    /**
     * @param mode
     *        the mode to set
     */
    public void setMode(final GameMode mode) {
        this.mode = mode;
    }

    /**
     * @param observerEncryptionKey
     *        the observerEncryptionKey to set
     */
    public void setObserverEncryptionKey(final String observerEncryptionKey) {
        this.observerEncryptionKey = observerEncryptionKey;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param players
     *        the players to set
     */
    public void setPlayers(final List<Participant> players) {
        this.players = players;
    }

    /**
     * @param queue
     *        the queue to set
     */
    public void setQueue(final Queue queue) {
        this.queue = queue;
    }

    /**
     * @param redTeamBans
     *        the redTeamBans to set
     */
    public void setRedTeamBans(final List<Integer> redTeamBans) {
        this.redTeamBans = redTeamBans;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final GameType type) {
        this.type = type;
    }
}
