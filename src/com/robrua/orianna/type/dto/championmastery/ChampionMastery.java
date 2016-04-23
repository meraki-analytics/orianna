package com.robrua.orianna.type.dto.championmastery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "championmastery")
public class ChampionMastery extends OriannaDto {
    private static final long serialVersionUID = 5932149700562755036L;
    private Long championId, championPointsSinceLastLevel, championPointsUntilNextLevel, lastPlayTime, playerId;
    private Integer championLevel, championPoints;
    private Boolean chestGranted;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private String highestGrade;

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
        if(championId == null) {
            if(other.championId != null) {
                return false;
            }
        }
        else if(!championId.equals(other.championId)) {
            return false;
        }
        if(lastPlayTime == null) {
            if(other.lastPlayTime != null) {
                return false;
            }
        }
        else if(!lastPlayTime.equals(other.lastPlayTime)) {
            return false;
        }
        if(playerId == null) {
            if(other.playerId != null) {
                return false;
            }
        }
        else if(!playerId.equals(other.playerId)) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public Long getChampionId() {
        return championId;
    }

    /**
     * @return the championLevel
     */
    public Integer getChampionLevel() {
        return championLevel;
    }

    /**
     * @return the championPoints
     */
    public Integer getChampionPoints() {
        return championPoints;
    }

    /**
     * @return the championPointsSinceLastLevel
     */
    public Long getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }

    /**
     * @return the championPointsUntilNextLevel
     */
    public Long getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    /**
     * @return the chestGranted
     */
    public Boolean getChestGranted() {
        return chestGranted;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the highestGrade
     */
    public String getHighestGrade() {
        return highestGrade;
    }

    /**
     * @return the lastPlayTime
     */
    public Long getLastPlayTime() {
        return lastPlayTime;
    }

    /**
     * @return the playerId
     */
    public Long getPlayerId() {
        return playerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (championId == null ? 0 : championId.hashCode());
        result = prime * result + (lastPlayTime == null ? 0 : lastPlayTime.hashCode());
        result = prime * result + (playerId == null ? 0 : playerId.hashCode());
        return result;
    }

    /**
     * @param championId
     *            the championId to set
     */
    public void setChampionId(final Long championId) {
        this.championId = championId;
    }

    /**
     * @param championLevel
     *            the championLevel to set
     */
    public void setChampionLevel(final Integer championLevel) {
        this.championLevel = championLevel;
    }

    /**
     * @param championPoints
     *            the championPoints to set
     */
    public void setChampionPoints(final Integer championPoints) {
        this.championPoints = championPoints;
    }

    /**
     * @param championPointsSinceLastLevel
     *            the championPointsSinceLastLevel to set
     */
    public void setChampionPointsSinceLastLevel(final Long championPointsSinceLastLevel) {
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    /**
     * @param championPointsUntilNextLevel
     *            the championPointsUntilNextLevel to set
     */
    public void setChampionPointsUntilNextLevel(final Long championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    /**
     * @param chestGranted
     *            the chestGranted to set
     */
    public void setChestGranted(final Boolean chestGranted) {
        this.chestGranted = chestGranted;
    }

    /**
     * @param highestGrade
     *            the highestGrade to set
     */
    public void setHighestGrade(final String highestGrade) {
        this.highestGrade = highestGrade;
    }

    /**
     * @param lastPlayTime
     *            the lastPlayTime to set
     */
    public void setLastPlayTime(final Long lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    /**
     * @param playerId
     *            the playerId to set
     */
    public void setPlayerId(final Long playerId) {
        this.playerId = playerId;
    }
}
