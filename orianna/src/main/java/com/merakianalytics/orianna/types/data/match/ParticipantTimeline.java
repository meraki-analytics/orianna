package com.merakianalytics.orianna.types.data.match;

import com.merakianalytics.orianna.types.data.CoreData;

public class ParticipantTimeline extends CoreData {
    private static final long serialVersionUID = 7991068856747791693L;
    private StatTotals creepScoreDifference, gold, experienceDifference, creepScore, experience, damageTakenDifference, damageTaken;
    private String lane, role;

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
        final ParticipantTimeline other = (ParticipantTimeline)obj;
        if(creepScore == null) {
            if(other.creepScore != null) {
                return false;
            }
        } else if(!creepScore.equals(other.creepScore)) {
            return false;
        }
        if(creepScoreDifference == null) {
            if(other.creepScoreDifference != null) {
                return false;
            }
        } else if(!creepScoreDifference.equals(other.creepScoreDifference)) {
            return false;
        }
        if(damageTaken == null) {
            if(other.damageTaken != null) {
                return false;
            }
        } else if(!damageTaken.equals(other.damageTaken)) {
            return false;
        }
        if(damageTakenDifference == null) {
            if(other.damageTakenDifference != null) {
                return false;
            }
        } else if(!damageTakenDifference.equals(other.damageTakenDifference)) {
            return false;
        }
        if(experience == null) {
            if(other.experience != null) {
                return false;
            }
        } else if(!experience.equals(other.experience)) {
            return false;
        }
        if(experienceDifference == null) {
            if(other.experienceDifference != null) {
                return false;
            }
        } else if(!experienceDifference.equals(other.experienceDifference)) {
            return false;
        }
        if(gold == null) {
            if(other.gold != null) {
                return false;
            }
        } else if(!gold.equals(other.gold)) {
            return false;
        }
        if(lane == null) {
            if(other.lane != null) {
                return false;
            }
        } else if(!lane.equals(other.lane)) {
            return false;
        }
        if(role == null) {
            if(other.role != null) {
                return false;
            }
        } else if(!role.equals(other.role)) {
            return false;
        }
        return true;
    }

    /**
     * @return the creepScore
     */
    public StatTotals getCreepScore() {
        return creepScore;
    }

    /**
     * @return the creepScoreDifference
     */
    public StatTotals getCreepScoreDifference() {
        return creepScoreDifference;
    }

    /**
     * @return the damageTaken
     */
    public StatTotals getDamageTaken() {
        return damageTaken;
    }

    /**
     * @return the damageTakenDifference
     */
    public StatTotals getDamageTakenDifference() {
        return damageTakenDifference;
    }

    /**
     * @return the experience
     */
    public StatTotals getExperience() {
        return experience;
    }

    /**
     * @return the experienceDifference
     */
    public StatTotals getExperienceDifference() {
        return experienceDifference;
    }

    /**
     * @return the gold
     */
    public StatTotals getGold() {
        return gold;
    }

    /**
     * @return the lane
     */
    public String getLane() {
        return lane;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (creepScore == null ? 0 : creepScore.hashCode());
        result = prime * result + (creepScoreDifference == null ? 0 : creepScoreDifference.hashCode());
        result = prime * result + (damageTaken == null ? 0 : damageTaken.hashCode());
        result = prime * result + (damageTakenDifference == null ? 0 : damageTakenDifference.hashCode());
        result = prime * result + (experience == null ? 0 : experience.hashCode());
        result = prime * result + (experienceDifference == null ? 0 : experienceDifference.hashCode());
        result = prime * result + (gold == null ? 0 : gold.hashCode());
        result = prime * result + (lane == null ? 0 : lane.hashCode());
        result = prime * result + (role == null ? 0 : role.hashCode());
        return result;
    }

    /**
     * @param creepScore
     *        the creepScore to set
     */
    public void setCreepScore(final StatTotals creepScore) {
        this.creepScore = creepScore;
    }

    /**
     * @param creepScoreDifference
     *        the creepScoreDifference to set
     */
    public void setCreepScoreDifference(final StatTotals creepScoreDifference) {
        this.creepScoreDifference = creepScoreDifference;
    }

    /**
     * @param damageTaken
     *        the damageTaken to set
     */
    public void setDamageTaken(final StatTotals damageTaken) {
        this.damageTaken = damageTaken;
    }

    /**
     * @param damageTakenDifference
     *        the damageTakenDifference to set
     */
    public void setDamageTakenDifference(final StatTotals damageTakenDifference) {
        this.damageTakenDifference = damageTakenDifference;
    }

    /**
     * @param experience
     *        the experience to set
     */
    public void setExperience(final StatTotals experience) {
        this.experience = experience;
    }

    /**
     * @param experienceDifference
     *        the experienceDifference to set
     */
    public void setExperienceDifference(final StatTotals experienceDifference) {
        this.experienceDifference = experienceDifference;
    }

    /**
     * @param gold
     *        the gold to set
     */
    public void setGold(final StatTotals gold) {
        this.gold = gold;
    }

    /**
     * @param lane
     *        the lane to set
     */
    public void setLane(final String lane) {
        this.lane = lane;
    }

    /**
     * @param role
     *        the role to set
     */
    public void setRole(final String role) {
        this.role = role;
    }
}
