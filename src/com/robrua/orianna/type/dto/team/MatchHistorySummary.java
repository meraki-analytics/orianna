package com.robrua.orianna.type.dto.team;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "matchhistorysummary")
public class MatchHistorySummary extends OriannaDto {
    private static final long serialVersionUID = 1520292597125046264L;
    private Integer assists, deaths, kills, mapId, opposingTeamKills;
    private Long date;
    @Id
    private Long gameId;
    private String gameMode, opposingTeamName;

    private Boolean invalid, win;

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
        if(!(obj instanceof MatchHistorySummary)) {
            return false;
        }
        final MatchHistorySummary other = (MatchHistorySummary)obj;
        if(assists == null) {
            if(other.assists != null) {
                return false;
            }
        }
        else if(!assists.equals(other.assists)) {
            return false;
        }
        if(date == null) {
            if(other.date != null) {
                return false;
            }
        }
        else if(!date.equals(other.date)) {
            return false;
        }
        if(deaths == null) {
            if(other.deaths != null) {
                return false;
            }
        }
        else if(!deaths.equals(other.deaths)) {
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
        if(gameMode == null) {
            if(other.gameMode != null) {
                return false;
            }
        }
        else if(!gameMode.equals(other.gameMode)) {
            return false;
        }
        if(invalid == null) {
            if(other.invalid != null) {
                return false;
            }
        }
        else if(!invalid.equals(other.invalid)) {
            return false;
        }
        if(kills == null) {
            if(other.kills != null) {
                return false;
            }
        }
        else if(!kills.equals(other.kills)) {
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
        if(opposingTeamKills == null) {
            if(other.opposingTeamKills != null) {
                return false;
            }
        }
        else if(!opposingTeamKills.equals(other.opposingTeamKills)) {
            return false;
        }
        if(opposingTeamName == null) {
            if(other.opposingTeamName != null) {
                return false;
            }
        }
        else if(!opposingTeamName.equals(other.opposingTeamName)) {
            return false;
        }
        if(win == null) {
            if(other.win != null) {
                return false;
            }
        }
        else if(!win.equals(other.win)) {
            return false;
        }
        return true;
    }

    /**
     * @return the assists
     */
    public Integer getAssists() {
        return assists;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "gameId";
        }
        return null;
    }

    /**
     * @return the date
     */
    public Long getDate() {
        return date;
    }

    /**
     * @return the deaths
     */
    public Integer getDeaths() {
        return deaths;
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
     * @return the invalid
     */
    public Boolean getInvalid() {
        return invalid;
    }

    /**
     * @return the kills
     */
    public Integer getKills() {
        return kills;
    }

    /**
     * @return the mapId
     */
    public Integer getMapId() {
        return mapId;
    }

    /**
     * @return the opposingTeamKills
     */
    public Integer getOpposingTeamKills() {
        return opposingTeamKills;
    }

    /**
     * @return the opposingTeamName
     */
    public String getOpposingTeamName() {
        return opposingTeamName;
    }

    /**
     * @return the win
     */
    public Boolean getWin() {
        return win;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (assists == null ? 0 : assists.hashCode());
        result = prime * result + (date == null ? 0 : date.hashCode());
        result = prime * result + (deaths == null ? 0 : deaths.hashCode());
        result = prime * result + (gameId == null ? 0 : gameId.hashCode());
        result = prime * result + (gameMode == null ? 0 : gameMode.hashCode());
        result = prime * result + (invalid == null ? 0 : invalid.hashCode());
        result = prime * result + (kills == null ? 0 : kills.hashCode());
        result = prime * result + (mapId == null ? 0 : mapId.hashCode());
        result = prime * result + (opposingTeamKills == null ? 0 : opposingTeamKills.hashCode());
        result = prime * result + (opposingTeamName == null ? 0 : opposingTeamName.hashCode());
        result = prime * result + (win == null ? 0 : win.hashCode());
        return result;
    }

    /**
     * @param assists
     *            the assists to set
     */
    public void setAssists(final Integer assists) {
        this.assists = assists;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(final Long date) {
        this.date = date;
    }

    /**
     * @param deaths
     *            the deaths to set
     */
    public void setDeaths(final Integer deaths) {
        this.deaths = deaths;
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
     * @param invalid
     *            the invalid to set
     */
    public void setInvalid(final Boolean invalid) {
        this.invalid = invalid;
    }

    /**
     * @param kills
     *            the kills to set
     */
    public void setKills(final Integer kills) {
        this.kills = kills;
    }

    /**
     * @param mapId
     *            the mapId to set
     */
    public void setMapId(final Integer mapId) {
        this.mapId = mapId;
    }

    /**
     * @param opposingTeamKills
     *            the opposingTeamKills to set
     */
    public void setOpposingTeamKills(final Integer opposingTeamKills) {
        this.opposingTeamKills = opposingTeamKills;
    }

    /**
     * @param opposingTeamName
     *            the opposingTeamName to set
     */
    public void setOpposingTeamName(final String opposingTeamName) {
        this.opposingTeamName = opposingTeamName;
    }

    /**
     * @param win
     *            the win to set
     */
    public void setWin(final Boolean win) {
        this.win = win;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatchHistorySummary [assists=" + assists + ", deaths=" + deaths + ", kills=" + kills + ", mapId=" + mapId + ", opposingTeamKills="
                + opposingTeamKills + ", date=" + date + ", gameId=" + gameId + ", gameMode=" + gameMode + ", opposingTeamName=" + opposingTeamName
                + ", invalid=" + invalid + ", win=" + win + "]";
    }
}
