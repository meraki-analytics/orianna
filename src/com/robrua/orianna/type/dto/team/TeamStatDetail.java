package com.robrua.orianna.type.dto.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "teammemberinfo")
public class TeamStatDetail extends OriannaDto {
    private static final long serialVersionUID = -3811741438279345755L;
    private Integer averageGamesPlayed, losses, wins;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private String teamStatType;

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

    /**
     * @return the averageGamesPlayed
     */
    public Integer getAverageGamesPlayed() {
        return averageGamesPlayed;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the losses
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     * @return the teamStatType
     */
    public String getTeamStatType() {
        return teamStatType;
    }

    /**
     * @return the wins
     */
    public Integer getWins() {
        return wins;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
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

    /**
     * @param averageGamesPlayed
     *            the averageGamesPlayed to set
     */
    public void setAverageGamesPlayed(final Integer averageGamesPlayed) {
        this.averageGamesPlayed = averageGamesPlayed;
    }

    /**
     * @param losses
     *            the losses to set
     */
    public void setLosses(final Integer losses) {
        this.losses = losses;
    }

    /**
     * @param teamStatType
     *            the teamStatType to set
     */
    public void setTeamStatType(final String teamStatType) {
        this.teamStatType = teamStatType;
    }

    /**
     * @param wins
     *            the wins to set
     */
    public void setWins(final Integer wins) {
        this.wins = wins;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TeamStatDetail [averageGamesPlayed=" + averageGamesPlayed + ", losses=" + losses + ", wins=" + wins + ", teamStatType=" + teamStatType + "]";
    }
}
