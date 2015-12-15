package com.robrua.orianna.type.dto.match;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "match.Team")
@Table(name = "matchteam")
public class Team extends OriannaDto {
    private static final long serialVersionUID = 7271221135056766741L;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BannedChampion> bans;
    private Integer baronKills, dragonKills, inhibitorKills, riftHeraldKills, teamId, towerKills, vilemawKills;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Long dominionVictoryScore;

    private Boolean firstBaron, firstBlood, firstDragon, firstInhibitor, firstRiftHerald, firstTower, winner;

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
        if(!(obj instanceof Team)) {
            return false;
        }
        final Team other = (Team)obj;
        if(bans == null) {
            if(other.bans != null) {
                return false;
            }
        }
        else if(!bans.equals(other.bans)) {
            return false;
        }
        if(baronKills == null) {
            if(other.baronKills != null) {
                return false;
            }
        }
        else if(!baronKills.equals(other.baronKills)) {
            return false;
        }
        if(dominionVictoryScore == null) {
            if(other.dominionVictoryScore != null) {
                return false;
            }
        }
        else if(!dominionVictoryScore.equals(other.dominionVictoryScore)) {
            return false;
        }
        if(dragonKills == null) {
            if(other.dragonKills != null) {
                return false;
            }
        }
        else if(!dragonKills.equals(other.dragonKills)) {
            return false;
        }
        if(firstBaron == null) {
            if(other.firstBaron != null) {
                return false;
            }
        }
        else if(!firstBaron.equals(other.firstBaron)) {
            return false;
        }
        if(firstBlood == null) {
            if(other.firstBlood != null) {
                return false;
            }
        }
        else if(!firstBlood.equals(other.firstBlood)) {
            return false;
        }
        if(firstDragon == null) {
            if(other.firstDragon != null) {
                return false;
            }
        }
        else if(!firstDragon.equals(other.firstDragon)) {
            return false;
        }
        if(firstRiftHerald == null) {
            if(other.firstRiftHerald != null) {
                return false;
            }
        }
        else if(!firstRiftHerald.equals(other.firstRiftHerald)) {
            return false;
        }
        if(firstInhibitor == null) {
            if(other.firstInhibitor != null) {
                return false;
            }
        }
        else if(!firstInhibitor.equals(other.firstInhibitor)) {
            return false;
        }
        if(firstTower == null) {
            if(other.firstTower != null) {
                return false;
            }
        }
        else if(!firstTower.equals(other.firstTower)) {
            return false;
        }
        if(inhibitorKills == null) {
            if(other.inhibitorKills != null) {
                return false;
            }
        }
        else if(!inhibitorKills.equals(other.inhibitorKills)) {
            return false;
        }
        if(riftHeraldKills == null) {
            if(other.riftHeraldKills != null) {
                return false;
            }
        }
        else if(!riftHeraldKills.equals(other.riftHeraldKills)) {
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
        if(towerKills == null) {
            if(other.towerKills != null) {
                return false;
            }
        }
        else if(!towerKills.equals(other.towerKills)) {
            return false;
        }
        if(vilemawKills == null) {
            if(other.vilemawKills != null) {
                return false;
            }
        }
        else if(!vilemawKills.equals(other.vilemawKills)) {
            return false;
        }
        if(winner == null) {
            if(other.winner != null) {
                return false;
            }
        }
        else if(!winner.equals(other.winner)) {
            return false;
        }
        return true;
    }

    /**
     * @return the bans
     */
    public List<BannedChampion> getBans() {
        return bans;
    }

    /**
     * @return the baronKills
     */
    public Integer getBaronKills() {
        return baronKills;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the dominionVictoryScore
     */
    public Long getDominionVictoryScore() {
        return dominionVictoryScore;
    }

    /**
     * @return the dragonKills
     */
    public Integer getDragonKills() {
        return dragonKills;
    }

    /**
     * @return the firstBaron
     */
    public Boolean getFirstBaron() {
        return firstBaron;
    }

    /**
     * @return the firstBlood
     */
    public Boolean getFirstBlood() {
        return firstBlood;
    }

    /**
     * @return the firstDragon
     */
    public Boolean getFirstDragon() {
        return firstDragon;
    }

    /**
     * @return the firstInhibitor
     */
    public Boolean getFirstInhibitor() {
        return firstInhibitor;
    }

    /**
     * @return the firstRiftHerald
     */
    public Boolean getFirstRiftHerald() {
        return firstRiftHerald;
    }

    /**
     * @return the firstTower
     */
    public Boolean getFirstTower() {
        return firstTower;
    }

    /**
     * @return the inhibitorKills
     */
    public Integer getInhibitorKills() {
        return inhibitorKills;
    }

    /**
     * @return the riftHeraldKills
     */
    public Integer getRiftHeraldKills() {
        return riftHeraldKills;
    }

    /**
     * @return the teamId
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @return the towerKills
     */
    public Integer getTowerKills() {
        return towerKills;
    }

    /**
     * @return the vilemawKills
     */
    public Integer getVilemawKills() {
        return vilemawKills;
    }

    /**
     * @return the winner
     */
    public Boolean getWinner() {
        return winner;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bans == null ? 0 : bans.hashCode());
        result = prime * result + (baronKills == null ? 0 : baronKills.hashCode());
        result = prime * result + (dominionVictoryScore == null ? 0 : dominionVictoryScore.hashCode());
        result = prime * result + (dragonKills == null ? 0 : dragonKills.hashCode());
        result = prime * result + (firstBaron == null ? 0 : firstBaron.hashCode());
        result = prime * result + (firstBlood == null ? 0 : firstBlood.hashCode());
        result = prime * result + (firstDragon == null ? 0 : firstDragon.hashCode());
        result = prime * result + (firstInhibitor == null ? 0 : firstInhibitor.hashCode());
        result = prime * result + (firstRiftHerald == null ? 0 : firstRiftHerald.hashCode());
        result = prime * result + (firstTower == null ? 0 : firstTower.hashCode());
        result = prime * result + (inhibitorKills == null ? 0 : inhibitorKills.hashCode());
        result = prime * result + (riftHeraldKills == null ? 0 : riftHeraldKills.hashCode());
        result = prime * result + (teamId == null ? 0 : teamId.hashCode());
        result = prime * result + (towerKills == null ? 0 : towerKills.hashCode());
        result = prime * result + (vilemawKills == null ? 0 : vilemawKills.hashCode());
        result = prime * result + (winner == null ? 0 : winner.hashCode());
        return result;
    }

    /**
     * @param bans
     *            the bans to set
     */
    public void setBans(final List<BannedChampion> bans) {
        this.bans = bans;
    }

    /**
     * @param baronKills
     *            the baronKills to set
     */
    public void setBaronKills(final Integer baronKills) {
        this.baronKills = baronKills;
    }

    /**
     * @param dominionVictoryScore
     *            the dominionVictoryScore to set
     */
    public void setDominionVictoryScore(final Long dominionVictoryScore) {
        this.dominionVictoryScore = dominionVictoryScore;
    }

    /**
     * @param dragonKills
     *            the dragonKills to set
     */
    public void setDragonKills(final Integer dragonKills) {
        this.dragonKills = dragonKills;
    }

    /**
     * @param firstBaron
     *            the firstBaron to set
     */
    public void setFirstBaron(final Boolean firstBaron) {
        this.firstBaron = firstBaron;
    }

    /**
     * @param firstBlood
     *            the firstBlood to set
     */
    public void setFirstBlood(final Boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

    /**
     * @param firstDragon
     *            the firstDragon to set
     */
    public void setFirstDragon(final Boolean firstDragon) {
        this.firstDragon = firstDragon;
    }

    /**
     * @param firstInhibitor
     *            the firstInhibitor to set
     */
    public void setFirstInhibitor(final Boolean firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    /**
     * @param firstRiftHerald
     *            the firstRiftHerald to set
     */
    public void setFirstRiftHerald(final Boolean firstRiftHerald) {
        this.firstRiftHerald = firstRiftHerald;
    }

    /**
     * @param firstTower
     *            the firstTower to set
     */
    public void setFirstTower(final Boolean firstTower) {
        this.firstTower = firstTower;
    }

    /**
     * @param inhibitorKills
     *            the inhibitorKills to set
     */
    public void setInhibitorKills(final Integer inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    /**
     * @param riftHeraldKills
     *            the riftHeraldKills to set
     */
    public void setRiftHeraldKills(final Integer riftHeraldKills) {
        this.riftHeraldKills = riftHeraldKills;
    }

    /**
     * @param teamId
     *            the teamId to set
     */
    public void setTeamId(final Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * @param towerKills
     *            the towerKills to set
     */
    public void setTowerKills(final Integer towerKills) {
        this.towerKills = towerKills;
    }

    /**
     * @param vilemawKills
     *            the vilemawKills to set
     */
    public void setVilemawKills(final Integer vilemawKills) {
        this.vilemawKills = vilemawKills;
    }

    /**
     * @param winner
     *            the winner to set
     */
    public void setWinner(final Boolean winner) {
        this.winner = winner;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Team [bans=" + bans + ", baronKills=" + baronKills + ", dragonKills=" + dragonKills + ", inhibitorKills=" + inhibitorKills
                + ", riftHeraldKills=" + riftHeraldKills + ", teamId=" + teamId + ", towerKills=" + towerKills + ", vilemawKills=" + vilemawKills
                + ", dominionVictoryScore=" + dominionVictoryScore + ", firstBaron=" + firstBaron + ", firstBlood=" + firstBlood + ", firstDragon="
                + firstDragon + ", firstInhibitor=" + firstInhibitor + ", firstRiftHerald=" + firstRiftHerald + ", firstTower=" + firstTower + ", winner="
                + winner + "]";
    }
}
