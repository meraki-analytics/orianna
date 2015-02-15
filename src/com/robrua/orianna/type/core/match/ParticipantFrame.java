package com.robrua.orianna.type.core.match;

import com.robrua.orianna.type.core.OriannaObject;

public class ParticipantFrame extends OriannaObject<com.robrua.orianna.type.dto.match.ParticipantFrame> {
    private static final long serialVersionUID = 5848777494748102697L;
    private Position position;

    /**
     * @param data
     *            the underlying dto
     */
    public ParticipantFrame(final com.robrua.orianna.type.dto.match.ParticipantFrame data) {
        super(data, com.robrua.orianna.type.dto.match.ParticipantFrame.class);
    }

    /**
     * Participant's current gold
     *
     * @return participant's current gold
     */
    public int getCurrentGold() {
        return super.getInteger(data.getCurrentGold());
    }

    /**
     * Dominion score of the participant
     *
     * @return dominion score of the participant
     */
    public int getDominionScore() {
        return super.getInteger(data.getDominionScore());
    }

    /**
     * Number of jungle minions killed by participant
     *
     * @return number of jungle minions killed by participant
     */
    public int getJungleMinionsKilled() {
        return super.getInteger(data.getJungleMinionsKilled());
    }

    /**
     * Participant's current level
     *
     * @return participant's current level
     */
    public int getLevel() {
        return super.getInteger(data.getLevel());
    }

    /**
     * Number of minions killed by participant
     *
     * @return number of minions killed by participant
     */
    public int getMinionsKilled() {
        return super.getInteger(data.getMinionsKilled());
    }

    /**
     * Participant ID
     *
     * @return participant ID
     */
    public int getParticipantId() {
        return super.getInteger(data.getParticipantId());
    }

    /**
     * Participant's position
     *
     * @return participant's position
     */
    public Position getPosition() {
        if(position == null) {
            position = new Position(data.getPosition());
        }

        return position;
    }

    /**
     * Team score of the participant
     *
     * @return team score of the participant
     */
    public int getTeamScore() {
        return super.getInteger(data.getTeamScore());
    }

    /**
     * Participant's total gold
     *
     * @return participant's total gold
     */
    public int getTotalGold() {
        return super.getInteger(data.getTotalGold());
    }

    /**
     * Experience earned by participant
     *
     * @return experience earned by participant
     */
    public int getXP() {
        return super.getInteger(data.getXp());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantFrame";
    }

}
