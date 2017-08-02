package com.merakianalytics.orianna.types.dto.match;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MatchParticipantFrame extends DataObject {
    private static final long serialVersionUID = -1475394108130028013L;
    private MatchPosition position;
    private int totalGold, teamScore, participantId, level, currentGold, minionsKilled, dominionScore, xp, jungleMinionsKilled;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final MatchParticipantFrame other = (MatchParticipantFrame)obj;
        if(currentGold != other.currentGold) {
            return false;
        }
        if(dominionScore != other.dominionScore) {
            return false;
        }
        if(jungleMinionsKilled != other.jungleMinionsKilled) {
            return false;
        }
        if(level != other.level) {
            return false;
        }
        if(minionsKilled != other.minionsKilled) {
            return false;
        }
        if(participantId != other.participantId) {
            return false;
        }
        if(position == null) {
            if(other.position != null) {
                return false;
            }
        } else if(!position.equals(other.position)) {
            return false;
        }
        if(teamScore != other.teamScore) {
            return false;
        }
        if(totalGold != other.totalGold) {
            return false;
        }
        if(xp != other.xp) {
            return false;
        }
        return true;
    }

    /**
     * @return the currentGold
     */
    public int getCurrentGold() {
        return currentGold;
    }

    /**
     * @return the dominionScore
     */
    public int getDominionScore() {
        return dominionScore;
    }

    /**
     * @return the jungleMinionsKilled
     */
    public int getJungleMinionsKilled() {
        return jungleMinionsKilled;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the minionsKilled
     */
    public int getMinionsKilled() {
        return minionsKilled;
    }

    /**
     * @return the participantId
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * @return the position
     */
    public MatchPosition getPosition() {
        return position;
    }

    /**
     * @return the teamScore
     */
    public int getTeamScore() {
        return teamScore;
    }

    /**
     * @return the totalGold
     */
    public int getTotalGold() {
        return totalGold;
    }

    /**
     * @return the xp
     */
    public int getXp() {
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
        result = prime * result + currentGold;
        result = prime * result + dominionScore;
        result = prime * result + jungleMinionsKilled;
        result = prime * result + level;
        result = prime * result + minionsKilled;
        result = prime * result + participantId;
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + teamScore;
        result = prime * result + totalGold;
        result = prime * result + xp;
        return result;
    }

    /**
     * @param currentGold
     *        the currentGold to set
     */
    public void setCurrentGold(final int currentGold) {
        this.currentGold = currentGold;
    }

    /**
     * @param dominionScore
     *        the dominionScore to set
     */
    public void setDominionScore(final int dominionScore) {
        this.dominionScore = dominionScore;
    }

    /**
     * @param jungleMinionsKilled
     *        the jungleMinionsKilled to set
     */
    public void setJungleMinionsKilled(final int jungleMinionsKilled) {
        this.jungleMinionsKilled = jungleMinionsKilled;
    }

    /**
     * @param level
     *        the level to set
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * @param minionsKilled
     *        the minionsKilled to set
     */
    public void setMinionsKilled(final int minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    /**
     * @param participantId
     *        the participantId to set
     */
    public void setParticipantId(final int participantId) {
        this.participantId = participantId;
    }

    /**
     * @param position
     *        the position to set
     */
    public void setPosition(final MatchPosition position) {
        this.position = position;
    }

    /**
     * @param teamScore
     *        the teamScore to set
     */
    public void setTeamScore(final int teamScore) {
        this.teamScore = teamScore;
    }

    /**
     * @param totalGold
     *        the totalGold to set
     */
    public void setTotalGold(final int totalGold) {
        this.totalGold = totalGold;
    }

    /**
     * @param xp
     *        the xp to set
     */
    public void setXp(final int xp) {
        this.xp = xp;
    }
}
