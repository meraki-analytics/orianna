package com.robrua.orianna.type.match;

import java.io.Serializable;

public class ParticipantFrame implements Serializable {
    private static final long serialVersionUID = -8388233958976359004L;
    public final Integer currentGold, jungleMinionsKilled, level, minionsKilled, totalGold, XP;
    public final Participant participant;
    public final Position position;

    public ParticipantFrame(final Integer currentGold, final Integer jungleMinionsKilled, final Integer level, final Integer minionsKilled,
            final Integer totalGold, final Integer XP, final Participant participant, final Position position) {
        this.currentGold = currentGold;
        this.jungleMinionsKilled = jungleMinionsKilled;
        this.level = level;
        this.minionsKilled = minionsKilled;
        this.totalGold = totalGold;
        this.XP = XP;
        this.participant = participant;
        this.position = position;
    }

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
        if(XP == null) {
            if(other.XP != null) {
                return false;
            }
        }
        else if(!XP.equals(other.XP)) {
            return false;
        }
        if(currentGold == null) {
            if(other.currentGold != null) {
                return false;
            }
        }
        else if(!currentGold.equals(other.currentGold)) {
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
        if(participant == null) {
            if(other.participant != null) {
                return false;
            }
        }
        else if(!participant.equals(other.participant)) {
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
        if(totalGold == null) {
            if(other.totalGold != null) {
                return false;
            }
        }
        else if(!totalGold.equals(other.totalGold)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (XP == null ? 0 : XP.hashCode());
        result = prime * result + (currentGold == null ? 0 : currentGold.hashCode());
        result = prime * result + (jungleMinionsKilled == null ? 0 : jungleMinionsKilled.hashCode());
        result = prime * result + (level == null ? 0 : level.hashCode());
        result = prime * result + (minionsKilled == null ? 0 : minionsKilled.hashCode());
        result = prime * result + (participant == null ? 0 : participant.hashCode());
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + (totalGold == null ? 0 : totalGold.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ParticipantFrame [participant=" + participant + "]";
    }
}
