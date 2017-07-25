package com.merakianalytics.orianna.type.dto.spectator;

import java.util.List;

import com.merakianalytics.orianna.type.dto.DataObject;

public class CurrentGameInfo extends DataObject {
    private static final long serialVersionUID = 1441488279750583051L;
    private List<BannedChampion> bannedChampions;
    private long gameId, gameStartTime, mapId, gameLength, gameQueueConfigId, summonerId;
    private Observer observers;
    private List<CurrentGameParticipant> participants;
    private String platformId, gameMode, gameType;

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
        final CurrentGameInfo other = (CurrentGameInfo)obj;
        if(bannedChampions == null) {
            if(other.bannedChampions != null) {
                return false;
            }
        } else if(!bannedChampions.equals(other.bannedChampions)) {
            return false;
        }
        if(gameId != other.gameId) {
            return false;
        }
        if(gameLength != other.gameLength) {
            return false;
        }
        if(gameMode == null) {
            if(other.gameMode != null) {
                return false;
            }
        } else if(!gameMode.equals(other.gameMode)) {
            return false;
        }
        if(gameQueueConfigId != other.gameQueueConfigId) {
            return false;
        }
        if(gameStartTime != other.gameStartTime) {
            return false;
        }
        if(gameType == null) {
            if(other.gameType != null) {
                return false;
            }
        } else if(!gameType.equals(other.gameType)) {
            return false;
        }
        if(mapId != other.mapId) {
            return false;
        }
        if(observers == null) {
            if(other.observers != null) {
                return false;
            }
        } else if(!observers.equals(other.observers)) {
            return false;
        }
        if(participants == null) {
            if(other.participants != null) {
                return false;
            }
        } else if(!participants.equals(other.participants)) {
            return false;
        }
        if(platformId == null) {
            if(other.platformId != null) {
                return false;
            }
        } else if(!platformId.equals(other.platformId)) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        return true;
    }

    /**
     * @return the bannedChampions
     */
    public List<BannedChampion> getBannedChampions() {
        return bannedChampions;
    }

    /**
     * @return the gameId
     */
    public long getGameId() {
        return gameId;
    }

    /**
     * @return the gameLength
     */
    public long getGameLength() {
        return gameLength;
    }

    /**
     * @return the gameMode
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * @return the gameQueueConfigId
     */
    public long getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    /**
     * @return the gameStartTime
     */
    public long getGameStartTime() {
        return gameStartTime;
    }

    /**
     * @return the gameType
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * @return the mapId
     */
    public long getMapId() {
        return mapId;
    }

    /**
     * @return the observers
     */
    public Observer getObservers() {
        return observers;
    }

    /**
     * @return the participants
     */
    public List<CurrentGameParticipant> getParticipants() {
        return participants;
    }

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @return the summonerId
     */
    public long getSummonerId() {
        return summonerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bannedChampions == null ? 0 : bannedChampions.hashCode());
        result = prime * result + (int)(gameId ^ gameId >>> 32);
        result = prime * result + (int)(gameLength ^ gameLength >>> 32);
        result = prime * result + (gameMode == null ? 0 : gameMode.hashCode());
        result = prime * result + (int)(gameQueueConfigId ^ gameQueueConfigId >>> 32);
        result = prime * result + (int)(gameStartTime ^ gameStartTime >>> 32);
        result = prime * result + (gameType == null ? 0 : gameType.hashCode());
        result = prime * result + (int)(mapId ^ mapId >>> 32);
        result = prime * result + (observers == null ? 0 : observers.hashCode());
        result = prime * result + (participants == null ? 0 : participants.hashCode());
        result = prime * result + (platformId == null ? 0 : platformId.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    /**
     * @param bannedChampions
     *        the bannedChampions to set
     */
    public void setBannedChampions(final List<BannedChampion> bannedChampions) {
        this.bannedChampions = bannedChampions;
    }

    /**
     * @param gameId
     *        the gameId to set
     */
    public void setGameId(final long gameId) {
        this.gameId = gameId;
    }

    /**
     * @param gameLength
     *        the gameLength to set
     */
    public void setGameLength(final long gameLength) {
        this.gameLength = gameLength;
    }

    /**
     * @param gameMode
     *        the gameMode to set
     */
    public void setGameMode(final String gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * @param gameQueueConfigId
     *        the gameQueueConfigId to set
     */
    public void setGameQueueConfigId(final long gameQueueConfigId) {
        this.gameQueueConfigId = gameQueueConfigId;
    }

    /**
     * @param gameStartTime
     *        the gameStartTime to set
     */
    public void setGameStartTime(final long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    /**
     * @param gameType
     *        the gameType to set
     */
    public void setGameType(final String gameType) {
        this.gameType = gameType;
    }

    /**
     * @param mapId
     *        the mapId to set
     */
    public void setMapId(final long mapId) {
        this.mapId = mapId;
    }

    /**
     * @param observers
     *        the observers to set
     */
    public void setObservers(final Observer observers) {
        this.observers = observers;
    }

    /**
     * @param participants
     *        the participants to set
     */
    public void setParticipants(final List<CurrentGameParticipant> participants) {
        this.participants = participants;
    }

    /**
     * @param platformId
     *        the platformId to set
     */
    public void setPlatformId(final String platformId) {
        this.platformId = platformId;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final long summonerId) {
        this.summonerId = summonerId;
    }
}
