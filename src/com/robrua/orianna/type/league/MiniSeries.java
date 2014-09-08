package com.robrua.orianna.type.league;

import java.io.Serializable;

public class MiniSeries implements Serializable {
    private static final long serialVersionUID = -1839659278762592342L;
    public final Integer losses, target, wins;
    public final String progress;

    public MiniSeries(final Integer losses, final Integer target, final Integer wins, final String progress) {
        this.losses = losses;
        this.target = target;
        this.wins = wins;
        this.progress = progress;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MiniSeries)) {
            return false;
        }
        final MiniSeries other = (MiniSeries)obj;
        if(losses == null) {
            if(other.losses != null) {
                return false;
            }
        }
        else if(!losses.equals(other.losses)) {
            return false;
        }
        if(progress == null) {
            if(other.progress != null) {
                return false;
            }
        }
        else if(!progress.equals(other.progress)) {
            return false;
        }
        if(target == null) {
            if(other.target != null) {
                return false;
            }
        }
        else if(!target.equals(other.target)) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (losses == null ? 0 : losses.hashCode());
        result = prime * result + (progress == null ? 0 : progress.hashCode());
        result = prime * result + (target == null ? 0 : target.hashCode());
        result = prime * result + (wins == null ? 0 : wins.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MiniSeries [progress=" + progress + "]";
    }
}
