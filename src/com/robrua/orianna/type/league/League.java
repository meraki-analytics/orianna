package com.robrua.orianna.type.league;

import java.io.Serializable;
import java.util.List;

public class League implements Serializable {
    private static final long serialVersionUID = -2139689143000233203L;
    public final List<LeagueEntry> entries;
    public final String name;
    public final LeagueType queue;
    public final Tier tier;

    public League(final List<LeagueEntry> entries, final LeagueType queue, final String name, final Tier tier) {
        this.entries = entries;
        this.queue = queue;
        this.name = name;
        this.tier = tier;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof League)) {
            return false;
        }
        final League other = (League)obj;
        if(entries == null) {
            if(other.entries != null) {
                return false;
            }
        }
        else if(!entries.equals(other.entries)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(queue != other.queue) {
            return false;
        }
        if(tier != other.tier) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (entries == null ? 0 : entries.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return tier + " [" + name + "]";
    }
}
