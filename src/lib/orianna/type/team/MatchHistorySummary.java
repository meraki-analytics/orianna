package lib.orianna.type.team;

import java.io.Serializable;
import java.time.LocalDateTime;

import lib.orianna.type.game.GameMap;

public class MatchHistorySummary implements Serializable {
    private static final long serialVersionUID = 4352310540648854805L;
    public final Integer assists, deaths, kills, opposingTeamKills;
    public final LocalDateTime date;
    public final Long gameID;
    public final String gameMode, opposingTeamName;
    public final Boolean invalid, win;
    public final GameMap map;

    public MatchHistorySummary(final Integer assists, final Integer deaths, final Integer kills, final Integer opposingTeamKills, final GameMap map,
            final LocalDateTime date, final Long gameID, final String gameMode, final String opposingTeamName, final Boolean invalid, final Boolean win) {
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
        this.opposingTeamKills = opposingTeamKills;
        this.map = map;
        this.date = date;
        this.gameID = gameID;
        this.gameMode = gameMode;
        this.opposingTeamName = opposingTeamName;
        this.invalid = invalid;
        this.win = win;
    }

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
        if(gameID == null) {
            if(other.gameID != null) {
                return false;
            }
        }
        else if(!gameID.equals(other.gameID)) {
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
        if(map != other.map) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (assists == null ? 0 : assists.hashCode());
        result = prime * result + (date == null ? 0 : date.hashCode());
        result = prime * result + (deaths == null ? 0 : deaths.hashCode());
        result = prime * result + (gameID == null ? 0 : gameID.hashCode());
        result = prime * result + (gameMode == null ? 0 : gameMode.hashCode());
        result = prime * result + (invalid == null ? 0 : invalid.hashCode());
        result = prime * result + (kills == null ? 0 : kills.hashCode());
        result = prime * result + (map == null ? 0 : map.hashCode());
        result = prime * result + (opposingTeamKills == null ? 0 : opposingTeamKills.hashCode());
        result = prime * result + (opposingTeamName == null ? 0 : opposingTeamName.hashCode());
        result = prime * result + (win == null ? 0 : win.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MatchHistorySummary [date=" + date + ", opposingTeamName=" + opposingTeamName + "]";
    }

}
