package com.merakianalytics.orianna.types.data.match;

import com.merakianalytics.orianna.types.data.CoreData;

public class ParticipantFrame extends CoreData {
    private static final long serialVersionUID = 7837530112594043529L;
    private int goldEarned, teamScore, level, gold, creepScore, dominionScore, experience, neutralMinionsKilled;
    private Position position;

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
        final ParticipantFrame other = (ParticipantFrame)obj;
        if(creepScore != other.creepScore) {
            return false;
        }
        if(dominionScore != other.dominionScore) {
            return false;
        }
        if(experience != other.experience) {
            return false;
        }
        if(gold != other.gold) {
            return false;
        }
        if(goldEarned != other.goldEarned) {
            return false;
        }
        if(level != other.level) {
            return false;
        }
        if(neutralMinionsKilled != other.neutralMinionsKilled) {
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
        return true;
    }

    /**
     * @return the creepScore
     */
    public int getCreepScore() {
        return creepScore;
    }

    /**
     * @return the dominionScore
     */
    public int getDominionScore() {
        return dominionScore;
    }

    /**
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @return the gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * @return the goldEarned
     */
    public int getGoldEarned() {
        return goldEarned;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the neutralMinionsKilled
     */
    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
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
    public int getTeamScore() {
        return teamScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + creepScore;
        result = prime * result + dominionScore;
        result = prime * result + experience;
        result = prime * result + gold;
        result = prime * result + goldEarned;
        result = prime * result + level;
        result = prime * result + neutralMinionsKilled;
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + teamScore;
        return result;
    }

    /**
     * @param creepScore
     *        the creepScore to set
     */
    public void setCreepScore(final int creepScore) {
        this.creepScore = creepScore;
    }

    /**
     * @param dominionScore
     *        the dominionScore to set
     */
    public void setDominionScore(final int dominionScore) {
        this.dominionScore = dominionScore;
    }

    /**
     * @param experience
     *        the experience to set
     */
    public void setExperience(final int experience) {
        this.experience = experience;
    }

    /**
     * @param gold
     *        the gold to set
     */
    public void setGold(final int gold) {
        this.gold = gold;
    }

    /**
     * @param goldEarned
     *        the goldEarned to set
     */
    public void setGoldEarned(final int goldEarned) {
        this.goldEarned = goldEarned;
    }

    /**
     * @param level
     *        the level to set
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * @param neutralMinionsKilled
     *        the neutralMinionsKilled to set
     */
    public void setNeutralMinionsKilled(final int neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    /**
     * @param position
     *        the position to set
     */
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * @param teamScore
     *        the teamScore to set
     */
    public void setTeamScore(final int teamScore) {
        this.teamScore = teamScore;
    }
}
