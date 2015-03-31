package com.robrua.orianna.type.dto.currentgame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "currentgame")
public class CurrentGameInfo extends OriannaDto {
    private static final long serialVersionUID = -8942022841946474617L;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BannedChampion> bannedChampions;
    @Id
    private Long gameId;

    private Long gameLength, gameQueueConfigId, gameStartTime, mapId;

    private String gameMode, gameType, platformId;

    @OneToOne(cascade = CascadeType.ALL)
    private Observer observers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Participant> participants;

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
        if(!(obj instanceof CurrentGameInfo)) {
            return false;
        }
        final CurrentGameInfo other = (CurrentGameInfo)obj;
        if(bannedChampions == null) {
            if(other.bannedChampions != null) {
                return false;
            }
        }
        else if(!bannedChampions.equals(other.bannedChampions)) {
            return false;
        }
        if(gameId == null) {
            if(other.gameId != null) {
                return false;
            }
        }
        else if(!gameId.equals(other.gameId)) {
            return false;
        }
        if(gameLength == null) {
            if(other.gameLength != null) {
                return false;
            }
        }
        else if(!gameLength.equals(other.gameLength)) {
            return false;
        }
        if(gameMode == null) {
            if(other.gameMode != null) {
                return false;
            }
        }
        else if(!gameMode.equals(other.gameMode)) {
            return false;
        }
        if(gameQueueConfigId == null) {
            if(other.gameQueueConfigId != null) {
                return false;
            }
        }
        else if(!gameQueueConfigId.equals(other.gameQueueConfigId)) {
            return false;
        }
        if(gameStartTime == null) {
            if(other.gameStartTime != null) {
                return false;
            }
        }
        else if(!gameStartTime.equals(other.gameStartTime)) {
            return false;
        }
        if(gameType == null) {
            if(other.gameType != null) {
                return false;
            }
        }
        else if(!gameType.equals(other.gameType)) {
            return false;
        }
        if(mapId == null) {
            if(other.mapId != null) {
                return false;
            }
        }
        else if(!mapId.equals(other.mapId)) {
            return false;
        }
        if(observers == null) {
            if(other.observers != null) {
                return false;
            }
        }
        else if(!observers.equals(other.observers)) {
            return false;
        }
        if(participants == null) {
            if(other.participants != null) {
                return false;
            }
        }
        else if(!participants.equals(other.participants)) {
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
        return true;
    }

    /**
     * @return the bannedChampions
     */
    public List<BannedChampion> getBannedChampions() {
        return bannedChampions;
    }

    /**
     * Gets all stored champion IDs for batch lookup
     *
     * @return the champion IDs
     */
    public Set<Long> getChampionIDs() {
        final Set<Long> set = new HashSet<>();
        for(final BannedChampion ban : bannedChampions) {
            set.add(ban.getChampionId());
        }
        for(final Participant p : participants) {
            set.add(p.getChampionId());
        }

        return set;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "gameId";
        }
        return null;
    }

    /**
     * @return the gameId
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * @return the gameLength
     */
    public Long getGameLength() {
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
    public Long getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    /**
     * @return the gameStartTime
     */
    public Long getGameStartTime() {
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
    public Long getMapId() {
        return mapId;
    }

    /**
     * Gets all stored mastery IDs for batch lookup
     *
     * @return the mastery IDs
     */
    public Set<Long> getMasteryIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            if(p.getMasteries() != null) {
                for(final Mastery m : p.getMasteries()) {
                    set.add(m.getMasteryId());
                }
            }
        }

        return set;
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
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * Gets all stored rune IDs for batch lookup
     *
     * @return the rune IDs
     */
    public Set<Long> getRuneIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            if(p.getRunes() != null) {
                for(final Rune rune : p.getRunes()) {
                    set.add(rune.getRuneId());
                }
            }
        }

        return set;
    }

    /**
     * Gets all stored summoner IDs for batch lookup
     *
     * @return the summoner IDs
     */
    public Set<Long> getSummonerIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            if(p.getSummonerId() != null) {
                set.add(p.getSummonerId());
            }
        }

        return set;
    }

    /**
     * Gets all stored summoner names for batch lookup
     *
     * @return the summoner names
     */
    public Set<String> getSummonerNames() {
        final Set<String> set = new HashSet<>();
        for(final Participant p : participants) {
            if(p.getSummonerName() != null) {
                set.add(p.getSummonerName());
            }
        }

        return set;
    }

    /**
     * Gets all stored summoner spell IDs for batch lookup
     *
     * @return the summoner spell IDs
     */
    public Set<Long> getSummonerSpellIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            set.add(p.getSpell1Id());
            set.add(p.getSpell2Id());
        }

        return set;
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
        result = prime * result + (gameId == null ? 0 : gameId.hashCode());
        result = prime * result + (gameLength == null ? 0 : gameLength.hashCode());
        result = prime * result + (gameMode == null ? 0 : gameMode.hashCode());
        result = prime * result + (gameQueueConfigId == null ? 0 : gameQueueConfigId.hashCode());
        result = prime * result + (gameStartTime == null ? 0 : gameStartTime.hashCode());
        result = prime * result + (gameType == null ? 0 : gameType.hashCode());
        result = prime * result + (mapId == null ? 0 : mapId.hashCode());
        result = prime * result + (observers == null ? 0 : observers.hashCode());
        result = prime * result + (participants == null ? 0 : participants.hashCode());
        result = prime * result + (platformId == null ? 0 : platformId.hashCode());
        return result;
    }

    /**
     * @param bannedChampions
     *            the bannedChampions to set
     */
    public void setBannedChampions(final List<BannedChampion> bannedChampions) {
        this.bannedChampions = bannedChampions;
    }

    /**
     * @param gameId
     *            the gameId to set
     */
    public void setGameId(final Long gameId) {
        this.gameId = gameId;
    }

    /**
     * @param gameLength
     *            the gameLength to set
     */
    public void setGameLength(final Long gameLength) {
        this.gameLength = gameLength;
    }

    /**
     * @param gameMode
     *            the gameMode to set
     */
    public void setGameMode(final String gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * @param gameQueueConfigId
     *            the gameQueueConfigId to set
     */
    public void setGameQueueConfigId(final Long gameQueueConfigId) {
        this.gameQueueConfigId = gameQueueConfigId;
    }

    /**
     * @param gameStartTime
     *            the gameStartTime to set
     */
    public void setGameStartTime(final Long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    /**
     * @param gameType
     *            the gameType to set
     */
    public void setGameType(final String gameType) {
        this.gameType = gameType;
    }

    /**
     * @param mapId
     *            the mapId to set
     */
    public void setMapId(final Long mapId) {
        this.mapId = mapId;
    }

    /**
     * @param observers
     *            the observers to set
     */
    public void setObservers(final Observer observers) {
        this.observers = observers;
    }

    /**
     * @param participants
     *            the participants to set
     */
    public void setParticipants(final List<Participant> participants) {
        this.participants = participants;
    }

    /**
     * @param platformId
     *            the platformId to set
     */
    public void setPlatformId(final String platformId) {
        this.platformId = platformId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CurrentGameInfo [bannedChampions=" + bannedChampions + ", gameId=" + gameId + ", gameLength=" + gameLength + ", gameQueueConfigId="
                + gameQueueConfigId + ", gameStartTime=" + gameStartTime + ", mapId=" + mapId + ", gameMode=" + gameMode + ", gameType=" + gameType
                + ", platformId=" + platformId + ", observers=" + observers + ", participants=" + participants + "]";
    }
}
