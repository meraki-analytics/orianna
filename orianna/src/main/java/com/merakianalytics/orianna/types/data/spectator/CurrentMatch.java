package com.merakianalytics.orianna.types.data.spectator;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.merakianalytics.orianna.types.data.CoreData;

public class CurrentMatch extends CoreData {
    private static final long serialVersionUID = 6191530707622315971L;
    private Team blueTeam, redTeam;
    private DateTime creationTime;
    private Duration duration;
    private long id, summonerId;
    private String mode, observerEncryptionKey, platform, type;
    private List<Player> players;
    private int queue, map;

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
        final CurrentMatch other = (CurrentMatch)obj;
        if(blueTeam == null) {
            if(other.blueTeam != null) {
                return false;
            }
        } else if(!blueTeam.equals(other.blueTeam)) {
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
        if(mode == null) {
            if(other.mode != null) {
                return false;
            }
        } else if(!mode.equals(other.mode)) {
            return false;
        }
        if(observerEncryptionKey == null) {
            if(other.observerEncryptionKey != null) {
                return false;
            }
        } else if(!observerEncryptionKey.equals(other.observerEncryptionKey)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
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
        if(redTeam == null) {
            if(other.redTeam != null) {
                return false;
            }
        } else if(!redTeam.equals(other.redTeam)) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        } else if(!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    /**
     * @return the blueTeam
     */
    public Team getBlueTeam() {
        return blueTeam;
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
    public int getMap() {
        return map;
    }

    /**
     * @return the mode
     */
    public String getMode() {
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
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return the queue
     */
    public int getQueue() {
        return queue;
    }

    /**
     * @return the redTeam
     */
    public Team getRedTeam() {
        return redTeam;
    }

    /**
     * @return the summonerId
     */
    public long getSummonerId() {
        return summonerId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (blueTeam == null ? 0 : blueTeam.hashCode());
        result = prime * result + (creationTime == null ? 0 : creationTime.hashCode());
        result = prime * result + (duration == null ? 0 : duration.hashCode());
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + map;
        result = prime * result + (mode == null ? 0 : mode.hashCode());
        result = prime * result + (observerEncryptionKey == null ? 0 : observerEncryptionKey.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (players == null ? 0 : players.hashCode());
        result = prime * result + queue;
        result = prime * result + (redTeam == null ? 0 : redTeam.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @param blueTeam
     *        the blueTeam to set
     */
    public void setBlueTeam(final Team blueTeam) {
        this.blueTeam = blueTeam;
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
    public void setMap(final int map) {
        this.map = map;
    }

    /**
     * @param mode
     *        the mode to set
     */
    public void setMode(final String mode) {
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
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param players
     *        the players to set
     */
    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    /**
     * @param queue
     *        the queue to set
     */
    public void setQueue(final int queue) {
        this.queue = queue;
    }

    /**
     * @param redTeam
     *        the redTeam to set
     */
    public void setRedTeam(final Team redTeam) {
        this.redTeam = redTeam;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final long summonerId) {
        this.summonerId = summonerId;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }
}
