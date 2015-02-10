package com.robrua.orianna.type.dto.game;

import java.util.List;

import com.robrua.orianna.type.dto.OriannaDto;

public class Game extends OriannaDto {
    private static final long serialVersionUID = -7399145898899391620L;
    private Integer championId, ipEarned, level, mapId, spell1, spell2, teamId;
    private Long createDate, gameId;
    private List<Player> fellowPlayers;
    private String gameMode, gameType, subType;
    private Boolean invalid;
    private RawStats stats;

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
        if(!(obj instanceof Game)) {
            return false;
        }
        final Game other = (Game)obj;
        if(gameId == null) {
            if(other.gameId != null) {
                return false;
            }
        }
        else if(!gameId.equals(other.gameId)) {
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

    /**
     * @return the createDate
     */
    public Long getCreateDate() {
        return createDate;
    }

    /**
     * @return the fellowPlayers
     */
    public List<Player> getFellowPlayers() {
        return fellowPlayers;
    }

    /**
     * @return the gameId
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * @return the gameMode
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * @return the gameType
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * @return the invalid
     */
    public Boolean getInvalid() {
        return invalid;
    }

    /**
     * @return the ipEarned
     */
    public Integer getIpEarned() {
        return ipEarned;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @return the mapId
     */
    public Integer getMapId() {
        return mapId;
    }

    /**
     * @return the spell1
     */
    public Integer getSpell1() {
        return spell1;
    }

    /**
     * @return the spell2
     */
    public Integer getSpell2() {
        return spell2;
    }

    /**
     * @return the stats
     */
    public RawStats getStats() {
        return stats;
    }

    /**
     * @return the subType
     */
    public String getSubType() {
        return subType;
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
        result = prime * result + (gameId == null ? 0 : gameId.hashCode());
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
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(final Long createDate) {
        this.createDate = createDate;
    }

    /**
     * @param fellowPlayers
     *            the fellowPlayers to set
     */
    public void setFellowPlayers(final List<Player> fellowPlayers) {
        this.fellowPlayers = fellowPlayers;
    }

    /**
     * @param gameId
     *            the gameId to set
     */
    public void setGameId(final Long gameId) {
        this.gameId = gameId;
    }

    /**
     * @param gameMode
     *            the gameMode to set
     */
    public void setGameMode(final String gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * @param gameType
     *            the gameType to set
     */
    public void setGameType(final String gameType) {
        this.gameType = gameType;
    }

    /**
     * @param invalid
     *            the invalid to set
     */
    public void setInvalid(final Boolean invalid) {
        this.invalid = invalid;
    }

    /**
     * @param ipEarned
     *            the ipEarned to set
     */
    public void setIpEarned(final Integer ipEarned) {
        this.ipEarned = ipEarned;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(final Integer level) {
        this.level = level;
    }

    /**
     * @param mapId
     *            the mapId to set
     */
    public void setMapId(final Integer mapId) {
        this.mapId = mapId;
    }

    /**
     * @param spell1
     *            the spell1 to set
     */
    public void setSpell1(final Integer spell1) {
        this.spell1 = spell1;
    }

    /**
     * @param spell2
     *            the spell2 to set
     */
    public void setSpell2(final Integer spell2) {
        this.spell2 = spell2;
    }

    /**
     * @param stats
     *            the stats to set
     */
    public void setStats(final RawStats stats) {
        this.stats = stats;
    }

    /**
     * @param subType
     *            the subType to set
     */
    public void setSubType(final String subType) {
        this.subType = subType;
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
        return "Game [championId=" + championId + ", ipEarned=" + ipEarned + ", level=" + level + ", mapId=" + mapId + ", spell1=" + spell1 + ", spell2="
                + spell2 + ", teamId=" + teamId + ", createDate=" + createDate + ", gameId=" + gameId + ", fellowPlayers=" + fellowPlayers + ", gameMode="
                + gameMode + ", gameType=" + gameType + ", subType=" + subType + ", invalid=" + invalid + ", stats=" + stats + "]";
    }
}
