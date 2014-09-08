package com.robrua.orianna.type.summoner;

import java.io.Serializable;

import com.robrua.orianna.type.staticdata.Mastery;
import com.robrua.orianna.type.staticdata.MasteryType;

public class MasterySlot implements Serializable {
    private static final long serialVersionUID = 754727306705168514L;
    public final Mastery mastery;
    public final Integer rank;
    public final MasteryType type;

    public MasterySlot(final Mastery mastery, final Integer rank, final MasteryType type) {
        this.mastery = mastery;
        this.rank = rank;
        this.type = type;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MasterySlot)) {
            return false;
        }
        final MasterySlot other = (MasterySlot)obj;
        if(mastery == null) {
            if(other.mastery != null) {
                return false;
            }
        }
        else if(!mastery.equals(other.mastery)) {
            return false;
        }
        if(rank == null) {
            if(other.rank != null) {
                return false;
            }
        }
        else if(!rank.equals(other.rank)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (mastery == null ? 0 : mastery.hashCode());
        result = prime * result + (rank == null ? 0 : rank.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MasterySlot [rank=" + rank + ", mastery=" + mastery + "]";
    }
}
