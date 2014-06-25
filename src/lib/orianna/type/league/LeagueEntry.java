package lib.orianna.type.league;

import java.io.Serializable;

import lib.orianna.type.summoner.Summoner;
import lib.orianna.type.team.Team;

public class LeagueEntry implements Serializable {
    private static final long serialVersionUID = -8275374872325116327L;
    public final String division, playerOrTeamName;
    public final Boolean isFreshBlood, isHotStreak, isInactive, isVeteran;
    public final Integer leaguePoints, wins;
    public final MiniSeries miniSeries;
    public final Summoner player;
    public final Team team;

    public LeagueEntry(final Summoner player, final String division, final String playerOrTeamName, final Boolean isFreshBlood, final Boolean isHotStreak,
            final Boolean isInactive, final Boolean isVeteran, final Integer leaguePoints, final Integer wins, final MiniSeries miniSeries) {
        this.player = player;
        this.division = division;
        this.playerOrTeamName = playerOrTeamName;
        this.isFreshBlood = isFreshBlood;
        this.isHotStreak = isHotStreak;
        this.isInactive = isInactive;
        this.isVeteran = isVeteran;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.miniSeries = miniSeries;
        team = null;
    }

    public LeagueEntry(final Team team, final String division, final String playerOrTeamName, final Boolean isFreshBlood, final Boolean isHotStreak,
            final Boolean isInactive, final Boolean isVeteran, final Integer leaguePoints, final Integer wins, final MiniSeries miniSeries) {
        this.team = team;
        this.division = division;
        this.playerOrTeamName = playerOrTeamName;
        this.isFreshBlood = isFreshBlood;
        this.isHotStreak = isHotStreak;
        this.isInactive = isInactive;
        this.isVeteran = isVeteran;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.miniSeries = miniSeries;
        player = null;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof LeagueEntry)) {
            return false;
        }
        final LeagueEntry other = (LeagueEntry)obj;
        if(division == null) {
            if(other.division != null) {
                return false;
            }
        }
        else if(!division.equals(other.division)) {
            return false;
        }
        if(isFreshBlood == null) {
            if(other.isFreshBlood != null) {
                return false;
            }
        }
        else if(!isFreshBlood.equals(other.isFreshBlood)) {
            return false;
        }
        if(isHotStreak == null) {
            if(other.isHotStreak != null) {
                return false;
            }
        }
        else if(!isHotStreak.equals(other.isHotStreak)) {
            return false;
        }
        if(isInactive == null) {
            if(other.isInactive != null) {
                return false;
            }
        }
        else if(!isInactive.equals(other.isInactive)) {
            return false;
        }
        if(isVeteran == null) {
            if(other.isVeteran != null) {
                return false;
            }
        }
        else if(!isVeteran.equals(other.isVeteran)) {
            return false;
        }
        if(leaguePoints == null) {
            if(other.leaguePoints != null) {
                return false;
            }
        }
        else if(!leaguePoints.equals(other.leaguePoints)) {
            return false;
        }
        if(miniSeries == null) {
            if(other.miniSeries != null) {
                return false;
            }
        }
        else if(!miniSeries.equals(other.miniSeries)) {
            return false;
        }
        if(player == null) {
            if(other.player != null) {
                return false;
            }
        }
        else if(!player.equals(other.player)) {
            return false;
        }
        if(playerOrTeamName == null) {
            if(other.playerOrTeamName != null) {
                return false;
            }
        }
        else if(!playerOrTeamName.equals(other.playerOrTeamName)) {
            return false;
        }
        if(team == null) {
            if(other.team != null) {
                return false;
            }
        }
        else if(!team.equals(other.team)) {
            return false;
        }
        if(wins == null) {
            if(other.wins != null) {
                return false;
            }
        }
        else if(!wins.equals(other.wins)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (division == null ? 0 : division.hashCode());
        result = prime * result + (isFreshBlood == null ? 0 : isFreshBlood.hashCode());
        result = prime * result + (isHotStreak == null ? 0 : isHotStreak.hashCode());
        result = prime * result + (isInactive == null ? 0 : isInactive.hashCode());
        result = prime * result + (isVeteran == null ? 0 : isVeteran.hashCode());
        result = prime * result + (leaguePoints == null ? 0 : leaguePoints.hashCode());
        result = prime * result + (miniSeries == null ? 0 : miniSeries.hashCode());
        result = prime * result + (player == null ? 0 : player.hashCode());
        result = prime * result + (playerOrTeamName == null ? 0 : playerOrTeamName.hashCode());
        result = prime * result + (team == null ? 0 : team.hashCode());
        result = prime * result + (wins == null ? 0 : wins.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "LeagueEntry [division=" + division + ", playerOrTeamName=" + playerOrTeamName + "]";
    }

}
