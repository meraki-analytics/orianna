package com.merakianalytics.orianna.types.data.championmastery;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionMastery extends CoreData {
    private static final long serialVersionUID = -316425968873497130L;
    private long championId, playerId, pointsUntilNextLevel, pointsSinceLastLevel;
    private boolean chestGranted;
    private DateTime lastPlayed;
    private int level, points, tokens;
    private Platform platform;

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
        final ChampionMastery other = (ChampionMastery)obj;
        if(championId != other.championId) {
            return false;
        }
        if(chestGranted != other.chestGranted) {
            return false;
        }
        if(lastPlayed == null) {
            if(other.lastPlayed != null) {
                return false;
            }
        } else if(!lastPlayed.equals(other.lastPlayed)) {
            return false;
        }
        if(level != other.level) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(playerId != other.playerId) {
            return false;
        }
        if(points != other.points) {
            return false;
        }
        if(pointsSinceLastLevel != other.pointsSinceLastLevel) {
            return false;
        }
        if(pointsUntilNextLevel != other.pointsUntilNextLevel) {
            return false;
        }
        if(tokens != other.tokens) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public long getChampionId() {
        return championId;
    }

    /**
     * @return the lastPlayed
     */
    public DateTime getLastPlayed() {
        return lastPlayed;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the playerId
     */
    public long getPlayerId() {
        return playerId;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return the pointsSinceLastLevel
     */
    public long getPointsSinceLastLevel() {
        return pointsSinceLastLevel;
    }

    /**
     * @return the pointsUntilNextLevel
     */
    public long getPointsUntilNextLevel() {
        return pointsUntilNextLevel;
    }

    /**
     * @return the tokens
     */
    public int getTokens() {
        return tokens;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(championId ^ championId >>> 32);
        result = prime * result + (chestGranted ? 1231 : 1237);
        result = prime * result + (lastPlayed == null ? 0 : lastPlayed.hashCode());
        result = prime * result + level;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(playerId ^ playerId >>> 32);
        result = prime * result + points;
        result = prime * result + (int)(pointsSinceLastLevel ^ pointsSinceLastLevel >>> 32);
        result = prime * result + (int)(pointsUntilNextLevel ^ pointsUntilNextLevel >>> 32);
        result = prime * result + tokens;
        return result;
    }

    /**
     * @return the chestGranted
     */
    public boolean isChestGranted() {
        return chestGranted;
    }

    /**
     * @param championId
     *        the championId to set
     */
    public void setChampionId(final long championId) {
        this.championId = championId;
    }

    /**
     * @param chestGranted
     *        the chestGranted to set
     */
    public void setChestGranted(final boolean chestGranted) {
        this.chestGranted = chestGranted;
    }

    /**
     * @param lastPlayed
     *        the lastPlayed to set
     */
    public void setLastPlayed(final DateTime lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    /**
     * @param level
     *        the level to set
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param playerId
     *        the playerId to set
     */
    public void setPlayerId(final long playerId) {
        this.playerId = playerId;
    }

    /**
     * @param points
     *        the points to set
     */
    public void setPoints(final int points) {
        this.points = points;
    }

    /**
     * @param pointsSinceLastLevel
     *        the pointsSinceLastLevel to set
     */
    public void setPointsSinceLastLevel(final long pointsSinceLastLevel) {
        this.pointsSinceLastLevel = pointsSinceLastLevel;
    }

    /**
     * @param pointsUntilNextLevel
     *        the pointsUntilNextLevel to set
     */
    public void setPointsUntilNextLevel(final long pointsUntilNextLevel) {
        this.pointsUntilNextLevel = pointsUntilNextLevel;
    }

    /**
     * @param tokens
     *        the tokens to set
     */
    public void setTokens(final int tokens) {
        this.tokens = tokens;
    }
}
