package com.merakianalytics.orianna.types.data.championmastery;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionMastery extends CoreData {
    private static final long serialVersionUID = -6045032013873774634L;
    private boolean chestGranted;
    private DateTime lastPlayed;
    private int level, points, tokens, championId, pointsUntilNextLevel, pointsSinceLastLevel;
    private String platform, puuid;

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
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
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
        if(puuid == null) {
            if(other.puuid != null) {
                return false;
            }
        } else if(!puuid.equals(other.puuid)) {
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
    public int getChampionId() {
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
    public String getPlatform() {
        return platform;
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
    public int getPointsSinceLastLevel() {
        return pointsSinceLastLevel;
    }

    /**
     * @return the pointsUntilNextLevel
     */
    public int getPointsUntilNextLevel() {
        return pointsUntilNextLevel;
    }

    /**
     * @return the puuid
     */
    public String getPuuid() {
        return puuid;
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
        result = prime * result + championId;
        result = prime * result + (chestGranted ? 1231 : 1237);
        result = prime * result + (lastPlayed == null ? 0 : lastPlayed.hashCode());
        result = prime * result + level;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + points;
        result = prime * result + pointsSinceLastLevel;
        result = prime * result + pointsUntilNextLevel;
        result = prime * result + (puuid == null ? 0 : puuid.hashCode());
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
    public void setChampionId(final int championId) {
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
    public void setPlatform(final String platform) {
        this.platform = platform;
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
    public void setPointsSinceLastLevel(final int pointsSinceLastLevel) {
        this.pointsSinceLastLevel = pointsSinceLastLevel;
    }

    /**
     * @param pointsUntilNextLevel
     *        the pointsUntilNextLevel to set
     */
    public void setPointsUntilNextLevel(final int pointsUntilNextLevel) {
        this.pointsUntilNextLevel = pointsUntilNextLevel;
    }

    /**
     * @param puuid
     *        the puuid to set
     */
    public void setPuuid(final String puuid) {
        this.puuid = puuid;
    }

    /**
     * @param tokens
     *        the tokens to set
     */
    public void setTokens(final int tokens) {
        this.tokens = tokens;
    }
}
