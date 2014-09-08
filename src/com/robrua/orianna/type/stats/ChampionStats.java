package com.robrua.orianna.type.stats;

import java.io.Serializable;

import com.robrua.orianna.type.staticdata.Champion;

public class ChampionStats implements Serializable {
    private static final long serialVersionUID = -2410046499512693667L;
    public final Champion champion;
    public final AggregatedStats stats;

    public ChampionStats(final Champion champion, final AggregatedStats stats) {
        this.champion = champion;
        this.stats = stats;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ChampionStats)) {
            return false;
        }
        final ChampionStats other = (ChampionStats)obj;
        if(champion == null) {
            if(other.champion != null) {
                return false;
            }
        }
        else if(!champion.equals(other.champion)) {
            return false;
        }
        if(stats == null) {
            if(other.stats != null) {
                return false;
            }
        }
        else if(!stats.equals(other.stats)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (champion == null ? 0 : champion.hashCode());
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ChampionStats [champion=" + champion + "]";
    }
}
