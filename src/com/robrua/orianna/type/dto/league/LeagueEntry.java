package com.robrua.orianna.type.dto.league;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "leagueentry")
public class LeagueEntry extends OriannaDto {
    private static final long serialVersionUID = -1460752030523463013L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private String division, playerOrTeamId, playerOrTeamName;
    private Boolean isFreshBlood, isHotStreak, isInactive, isVeteran;

    private Integer leaguePoints, losses, wins;

    @OneToOne(cascade = CascadeType.ALL)
    private MiniSeries miniSeries;

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
        if(losses == null) {
            if(other.losses != null) {
                return false;
            }
        }
        else if(!losses.equals(other.losses)) {
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
        if(playerOrTeamId == null) {
            if(other.playerOrTeamId != null) {
                return false;
            }
        }
        else if(!playerOrTeamId.equals(other.playerOrTeamId)) {
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
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the isFreshBlood
     */
    public Boolean getIsFreshBlood() {
        return isFreshBlood;
    }

    /**
     * @return the isHotStreak
     */
    public Boolean getIsHotStreak() {
        return isHotStreak;
    }

    /**
     * @return the isInactive
     */
    public Boolean getIsInactive() {
        return isInactive;
    }

    /**
     * @return the isVeteran
     */
    public Boolean getIsVeteran() {
        return isVeteran;
    }

    /**
     * @return the leaguePoints
     */
    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    /**
     * @return the losses
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     * @return the miniSeries
     */
    public MiniSeries getMiniSeries() {
        return miniSeries;
    }

    /**
     * @return the playerOrTeamId
     */
    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    /**
     * @return the playerOrTeamName
     */
    public String getPlayerOrTeamName() {
        return playerOrTeamName;
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
        result = prime * result + (division == null ? 0 : division.hashCode());
        result = prime * result + (isFreshBlood == null ? 0 : isFreshBlood.hashCode());
        result = prime * result + (isHotStreak == null ? 0 : isHotStreak.hashCode());
        result = prime * result + (isInactive == null ? 0 : isInactive.hashCode());
        result = prime * result + (isVeteran == null ? 0 : isVeteran.hashCode());
        result = prime * result + (leaguePoints == null ? 0 : leaguePoints.hashCode());
        result = prime * result + (losses == null ? 0 : losses.hashCode());
        result = prime * result + (miniSeries == null ? 0 : miniSeries.hashCode());
        result = prime * result + (playerOrTeamId == null ? 0 : playerOrTeamId.hashCode());
        result = prime * result + (playerOrTeamName == null ? 0 : playerOrTeamName.hashCode());
        result = prime * result + (wins == null ? 0 : wins.hashCode());
        return result;
    }

    /**
     * @param division
     *            the division to set
     */
    public void setDivision(final String division) {
        this.division = division;
    }

    /**
     * @param isFreshBlood
     *            the isFreshBlood to set
     */
    public void setIsFreshBlood(final Boolean isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    /**
     * @param isHotStreak
     *            the isHotStreak to set
     */
    public void setIsHotStreak(final Boolean isHotStreak) {
        this.isHotStreak = isHotStreak;
    }

    /**
     * @param isInactive
     *            the isInactive to set
     */
    public void setIsInactive(final Boolean isInactive) {
        this.isInactive = isInactive;
    }

    /**
     * @param isVeteran
     *            the isVeteran to set
     */
    public void setIsVeteran(final Boolean isVeteran) {
        this.isVeteran = isVeteran;
    }

    /**
     * @param leaguePoints
     *            the leaguePoints to set
     */
    public void setLeaguePoints(final Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    /**
     * @param losses
     *            the losses to set
     */
    public void setLosses(final Integer losses) {
        this.losses = losses;
    }

    /**
     * @param miniSeries
     *            the miniSeries to set
     */
    public void setMiniSeries(final MiniSeries miniSeries) {
        this.miniSeries = miniSeries;
    }

    /**
     * @param playerOrTeamId
     *            the playerOrTeamId to set
     */
    public void setPlayerOrTeamId(final String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    /**
     * @param playerOrTeamName
     *            the playerOrTeamName to set
     */
    public void setPlayerOrTeamName(final String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
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
        return "LeagueEntry [division=" + division + ", playerOrTeamId=" + playerOrTeamId + ", playerOrTeamName=" + playerOrTeamName + ", isFreshBlood="
                + isFreshBlood + ", isHotStreak=" + isHotStreak + ", isInactive=" + isInactive + ", isVeteran=" + isVeteran + ", leaguePoints=" + leaguePoints
                + ", losses=" + losses + ", wins=" + wins + ", miniSeries=" + miniSeries + "]";
    }
}
