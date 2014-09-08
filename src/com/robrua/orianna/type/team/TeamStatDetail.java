package com.robrua.orianna.type.team;

import java.io.Serializable;

public class TeamStatDetail implements Serializable {
    private static final long serialVersionUID = -8535394163123253728L;
    public final Integer averageGamesPlayed, losses, wins;
    public final String teamStatType;

    public TeamStatDetail(final Integer averageGamesPlayed, final Integer losses, final Integer wins, final String teamStatType) {
        this.averageGamesPlayed = averageGamesPlayed;
        this.losses = losses;
        this.wins = wins;
        this.teamStatType = teamStatType;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof TeamStatDetail)) {
            return false;
        }
        final TeamStatDetail other = (TeamStatDetail)obj;
        if(averageGamesPlayed == null) {
            if(other.averageGamesPlayed != null) {
                return false;
            }
        }
        else if(!averageGamesPlayed.equals(other.averageGamesPlayed)) {
            return false;
        }
        if(losses == null) {
            if(other.losses != null) {
                return false;
            }
        }
        else if(!losses.equals(other.losses)) {
            return false;
        }
        if(teamStatType == null) {
            if(other.teamStatType != null) {
                return false;
            }
        }
        else if(!teamStatType.equals(other.teamStatType)) {
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
        result = prime * result + (averageGamesPlayed == null ? 0 : averageGamesPlayed.hashCode());
        result = prime * result + (losses == null ? 0 : losses.hashCode());
        result = prime * result + (teamStatType == null ? 0 : teamStatType.hashCode());
        result = prime * result + (wins == null ? 0 : wins.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TeamStatDetail [averageGamesPlayed=" + averageGamesPlayed + ", losses=" + losses + ", wins=" + wins + ", teamStatType=" + teamStatType + "]";
    }
}
