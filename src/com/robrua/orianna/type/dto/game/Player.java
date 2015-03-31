package com.robrua.orianna.type.dto.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "game.Player")
@Table(name = "gameplayer")
public class Player extends OriannaDto {
    private static final long serialVersionUID = -2777208323484807265L;
    private Integer championId, teamId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Long summonerId;

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
        if(!(obj instanceof Player)) {
            return false;
        }
        final Player other = (Player)obj;
        if(championId == null) {
            if(other.championId != null) {
                return false;
            }
        }
        else if(!championId.equals(other.championId)) {
            return false;
        }
        if(summonerId == null) {
            if(other.summonerId != null) {
                return false;
            }
        }
        else if(!summonerId.equals(other.summonerId)) {
            return false;
        }
        if(teamId == null) {
            if(other.teamId != null) {
                return false;
            }
        }
        else if(!teamId.equals(other.teamId)) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public Integer getChampionId() {
        return championId;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the summonerId
     */
    public Long getSummonerId() {
        return summonerId;
    }

    /**
     * @return the teamId
     */
    public Integer getTeamId() {
        return teamId;
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
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        result = prime * result + (teamId == null ? 0 : teamId.hashCode());
        return result;
    }

    /**
     * @param championId
     *            the championId to set
     */
    public void setChampionId(final Integer championId) {
        this.championId = championId;
    }

    /**
     * @param summonerId
     *            the summonerId to set
     */
    public void setSummonerId(final Long summonerId) {
        this.summonerId = summonerId;
    }

    /**
     * @param teamId
     *            the teamId to set
     */
    public void setTeamId(final Integer teamId) {
        this.teamId = teamId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Player [championId=" + championId + ", teamId=" + teamId + ", summonerId=" + summonerId + "]";
    }
}
