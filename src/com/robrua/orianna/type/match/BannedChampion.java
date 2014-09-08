package com.robrua.orianna.type.match;

import java.io.Serializable;

import com.robrua.orianna.type.staticdata.Champion;

public class BannedChampion implements Serializable {
    private static final long serialVersionUID = -8938325018346686698L;
    public final Champion champion;
    public final Integer pickTurn;
    public final Side team;

    public BannedChampion(final Champion champion, final Integer pickTurn, final Side team) {
        this.champion = champion;
        this.pickTurn = pickTurn;
        this.team = team;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof BannedChampion)) {
            return false;
        }
        final BannedChampion other = (BannedChampion)obj;
        if(champion == null) {
            if(other.champion != null) {
                return false;
            }
        }
        else if(!champion.equals(other.champion)) {
            return false;
        }
        if(pickTurn == null) {
            if(other.pickTurn != null) {
                return false;
            }
        }
        else if(!pickTurn.equals(other.pickTurn)) {
            return false;
        }
        if(team == null) {
            if(other.team != null) {
                return false;
            }
        }
        else if(!team.equals(other.team)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (champion == null ? 0 : champion.hashCode());
        result = prime * result + (pickTurn == null ? 0 : pickTurn.hashCode());
        result = prime * result + (team == null ? 0 : team.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BannedChampion [champion=" + champion + ", pickTurn=" + pickTurn + ", team=" + team + "]";
    }
}
