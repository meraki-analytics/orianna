package com.merakianalytics.orianna.types.data.match;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.data.CoreData;

public class Match extends CoreData {
    private static final long serialVersionUID = -3669362861954900455L;
    private Team blueTeam, redTeam;
    private DateTime creationTime;
    private Duration duration;
    private long id, forAccountId;
    private Map map;
    private GameMode mode;
    private Platform platform;
    private List<Participant> players;
    private Queue queue;
    private Season season;
    private GameType type;
    private String version, tournamentCode;

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
        final Match other = (Match)obj;
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
        if(forAccountId != other.forAccountId) {
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
        if(redTeam == null) {
            if(other.redTeam != null) {
                return false;
            }
        } else if(!redTeam.equals(other.redTeam)) {
            return false;
        }
        if(season != other.season) {
            return false;
        }
        if(tournamentCode == null) {
            if(other.tournamentCode != null) {
                return false;
            }
        } else if(!tournamentCode.equals(other.tournamentCode)) {
            return false;
        }
        if(type != other.type) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
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
     * @return the forAccountId
     */
    public long getForAccountId() {
        return forAccountId;
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
     * @return the redTeam
     */
    public Team getRedTeam() {
        return redTeam;
    }

    /**
     * @return the season
     */
    public Season getSeason() {
        return season;
    }

    /**
     * @return the tournamentCode
     */
    public String getTournamentCode() {
        return tournamentCode;
    }

    /**
     * @return the type
     */
    public GameType getType() {
        return type;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (blueTeam == null ? 0 : blueTeam.hashCode());
        result = prime * result + (creationTime == null ? 0 : creationTime.hashCode());
        result = prime * result + (duration == null ? 0 : duration.hashCode());
        result = prime * result + (int)(forAccountId ^ forAccountId >>> 32);
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (map == null ? 0 : map.hashCode());
        result = prime * result + (mode == null ? 0 : mode.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (players == null ? 0 : players.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (redTeam == null ? 0 : redTeam.hashCode());
        result = prime * result + (season == null ? 0 : season.hashCode());
        result = prime * result + (tournamentCode == null ? 0 : tournamentCode.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
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
     * @param forAccountId
     *        the forAccountId to set
     */
    public void setForAccountId(final long forAccountId) {
        this.forAccountId = forAccountId;
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
     * @param redTeam
     *        the redTeam to set
     */
    public void setRedTeam(final Team redTeam) {
        this.redTeam = redTeam;
    }

    /**
     * @param season
     *        the season to set
     */
    public void setSeason(final Season season) {
        this.season = season;
    }

    /**
     * @param tournamentCode
     *        the tournamentCode to set
     */
    public void setTournamentCode(final String tournamentCode) {
        this.tournamentCode = tournamentCode;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final GameType type) {
        this.type = type;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
