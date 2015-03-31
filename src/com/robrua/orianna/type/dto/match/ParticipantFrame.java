package com.robrua.orianna.type.dto.match;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "participantframe")
public class ParticipantFrame extends OriannaDto {
    private static final long serialVersionUID = 3116174959429857810L;
    private Integer currentGold, jungleMinionsKilled, level, minionsKilled, participantId, totalGold, xp, dominionScore, teamScore;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @OneToOne(cascade = CascadeType.ALL)
    private Position position;

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
        if(!(obj instanceof ParticipantFrame)) {
            return false;
        }
        final ParticipantFrame other = (ParticipantFrame)obj;
        if(currentGold == null) {
            if(other.currentGold != null) {
                return false;
            }
        }
        else if(!currentGold.equals(other.currentGold)) {
            return false;
        }
        if(dominionScore == null) {
            if(other.dominionScore != null) {
                return false;
            }
        }
        else if(!dominionScore.equals(other.dominionScore)) {
            return false;
        }
        if(jungleMinionsKilled == null) {
            if(other.jungleMinionsKilled != null) {
                return false;
            }
        }
        else if(!jungleMinionsKilled.equals(other.jungleMinionsKilled)) {
            return false;
        }
        if(level == null) {
            if(other.level != null) {
                return false;
            }
        }
        else if(!level.equals(other.level)) {
            return false;
        }
        if(minionsKilled == null) {
            if(other.minionsKilled != null) {
                return false;
            }
        }
        else if(!minionsKilled.equals(other.minionsKilled)) {
            return false;
        }
        if(participantId == null) {
            if(other.participantId != null) {
                return false;
            }
        }
        else if(!participantId.equals(other.participantId)) {
            return false;
        }
        if(position == null) {
            if(other.position != null) {
                return false;
            }
        }
        else if(!position.equals(other.position)) {
            return false;
        }
        if(teamScore == null) {
            if(other.teamScore != null) {
                return false;
            }
        }
        else if(!teamScore.equals(other.teamScore)) {
            return false;
        }
        if(totalGold == null) {
            if(other.totalGold != null) {
                return false;
            }
        }
        else if(!totalGold.equals(other.totalGold)) {
            return false;
        }
        if(xp == null) {
            if(other.xp != null) {
                return false;
            }
        }
        else if(!xp.equals(other.xp)) {
            return false;
        }
        return true;
    }

    /**
     * @return the currentGold
     */
    public Integer getCurrentGold() {
        return currentGold;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the dominionScore
     */
    public Integer getDominionScore() {
        return dominionScore;
    }

    /**
     * @return the jungleMinionsKilled
     */
    public Integer getJungleMinionsKilled() {
        return jungleMinionsKilled;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @return the minionsKilled
     */
    public Integer getMinionsKilled() {
        return minionsKilled;
    }

    /**
     * @return the participantId
     */
    public Integer getParticipantId() {
        return participantId;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @return the teamScore
     */
    public Integer getTeamScore() {
        return teamScore;
    }

    /**
     * @return the totalGold
     */
    public Integer getTotalGold() {
        return totalGold;
    }

    /**
     * @return the xp
     */
    public Integer getXp() {
        return xp;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (currentGold == null ? 0 : currentGold.hashCode());
        result = prime * result + (dominionScore == null ? 0 : dominionScore.hashCode());
        result = prime * result + (jungleMinionsKilled == null ? 0 : jungleMinionsKilled.hashCode());
        result = prime * result + (level == null ? 0 : level.hashCode());
        result = prime * result + (minionsKilled == null ? 0 : minionsKilled.hashCode());
        result = prime * result + (participantId == null ? 0 : participantId.hashCode());
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + (teamScore == null ? 0 : teamScore.hashCode());
        result = prime * result + (totalGold == null ? 0 : totalGold.hashCode());
        result = prime * result + (xp == null ? 0 : xp.hashCode());
        return result;
    }

    /**
     * @param currentGold
     *            the currentGold to set
     */
    public void setCurrentGold(final Integer currentGold) {
        this.currentGold = currentGold;
    }

    /**
     * @param dominionScore
     *            the dominionScore to set
     */
    public void setDominionScore(final Integer dominionScore) {
        this.dominionScore = dominionScore;
    }

    /**
     * @param jungleMinionsKilled
     *            the jungleMinionsKilled to set
     */
    public void setJungleMinionsKilled(final Integer jungleMinionsKilled) {
        this.jungleMinionsKilled = jungleMinionsKilled;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(final Integer level) {
        this.level = level;
    }

    /**
     * @param minionsKilled
     *            the minionsKilled to set
     */
    public void setMinionsKilled(final Integer minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    /**
     * @param participantId
     *            the participantId to set
     */
    public void setParticipantId(final Integer participantId) {
        this.participantId = participantId;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * @param teamScore
     *            the teamScore to set
     */
    public void setTeamScore(final Integer teamScore) {
        this.teamScore = teamScore;
    }

    /**
     * @param totalGold
     *            the totalGold to set
     */
    public void setTotalGold(final Integer totalGold) {
        this.totalGold = totalGold;
    }

    /**
     * @param xp
     *            the xp to set
     */
    public void setXp(final Integer xp) {
        this.xp = xp;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantFrame [currentGold=" + currentGold + ", jungleMinionsKilled=" + jungleMinionsKilled + ", level=" + level + ", minionsKilled="
                + minionsKilled + ", participantId=" + participantId + ", totalGold=" + totalGold + ", xp=" + xp + ", dominionScore=" + dominionScore
                + ", teamScore=" + teamScore + ", position=" + position + "]";
    }
}
