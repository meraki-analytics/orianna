package com.merakianalytics.orianna.type.dto.championmastery;

import com.merakianalytics.orianna.type.dto.DataObject;

public class ChampionMastery extends DataObject {
    private static final long serialVersionUID = 2924340461805546618L;
    private long championId, playerId, championPointsUntilNextLevel, championPointsSinceLastLevel, lastPlayTime;
    private int championLevel, championPoints, tokensEarned;
    private boolean chestGranted;
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
        final ChampionMastery other = (ChampionMastery)obj;
        if(championId != other.championId) {
            return false;
        }
        if(championLevel != other.championLevel) {
            return false;
        }
        if(championPoints != other.championPoints) {
            return false;
        }
        if(championPointsSinceLastLevel != other.championPointsSinceLastLevel) {
            return false;
        }
        if(championPointsUntilNextLevel != other.championPointsUntilNextLevel) {
            return false;
        }
        if(chestGranted != other.chestGranted) {
            return false;
        }
        if(lastPlayTime != other.lastPlayTime) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(playerId != other.playerId) {
            return false;
        }
        if(tokensEarned != other.tokensEarned) {
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
     * @return the championLevel
     */
    public int getChampionLevel() {
        return championLevel;
    }

    /**
     * @return the championPoints
     */
    public int getChampionPoints() {
        return championPoints;
    }

    /**
     * @return the championPointsSinceLastLevel
     */
    public long getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }

    /**
     * @return the championPointsUntilNextLevel
     */
    public long getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    /**
     * @return the lastPlayTime
     */
    public long getLastPlayTime() {
        return lastPlayTime;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the playerId
     */
    public long getPlayerId() {
        return playerId;
    }

    /**
     * @return the tokensEarned
     */
    public int getTokensEarned() {
        return tokensEarned;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(championId ^ championId >>> 32);
        result = prime * result + championLevel;
        result = prime * result + championPoints;
        result = prime * result + (int)(championPointsSinceLastLevel ^ championPointsSinceLastLevel >>> 32);
        result = prime * result + (int)(championPointsUntilNextLevel ^ championPointsUntilNextLevel >>> 32);
        result = prime * result + (chestGranted ? 1231 : 1237);
        result = prime * result + (int)(lastPlayTime ^ lastPlayTime >>> 32);
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(playerId ^ playerId >>> 32);
        result = prime * result + tokensEarned;
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
     * @param championLevel
     *        the championLevel to set
     */
    public void setChampionLevel(final int championLevel) {
        this.championLevel = championLevel;
    }

    /**
     * @param championPoints
     *        the championPoints to set
     */
    public void setChampionPoints(final int championPoints) {
        this.championPoints = championPoints;
    }

    /**
     * @param championPointsSinceLastLevel
     *        the championPointsSinceLastLevel to set
     */
    public void setChampionPointsSinceLastLevel(final long championPointsSinceLastLevel) {
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    /**
     * @param championPointsUntilNextLevel
     *        the championPointsUntilNextLevel to set
     */
    public void setChampionPointsUntilNextLevel(final long championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    /**
     * @param chestGranted
     *        the chestGranted to set
     */
    public void setChestGranted(final boolean chestGranted) {
        this.chestGranted = chestGranted;
    }

    /**
     * @param lastPlayTime
     *        the lastPlayTime to set
     */
    public void setLastPlayTime(final long lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
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
     * @param tokensEarned
     *        the tokensEarned to set
     */
    public void setTokensEarned(final int tokensEarned) {
        this.tokensEarned = tokensEarned;
    }
}
