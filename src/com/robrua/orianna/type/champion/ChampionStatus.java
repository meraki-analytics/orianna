package com.robrua.orianna.type.champion;

import java.io.Serializable;

import com.robrua.orianna.type.staticdata.Champion;

public class ChampionStatus implements Serializable {
    private static final long serialVersionUID = -3432372939041377924L;
    public final Boolean active, botEnabled, botMmEnabled, freeToPlay, rankedPlayEnabled;
    public final Champion champion;

    public ChampionStatus(final Boolean active, final Boolean botEnabled, final Boolean botMmEnabled, final Boolean freeToPlay,
            final Boolean rankedPlayEnabled, final Champion champion) {
        this.active = active;
        this.botEnabled = botEnabled;
        this.botMmEnabled = botMmEnabled;
        this.freeToPlay = freeToPlay;
        this.rankedPlayEnabled = rankedPlayEnabled;
        this.champion = champion;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ChampionStatus)) {
            return false;
        }
        final ChampionStatus other = (ChampionStatus)obj;
        if(active == null) {
            if(other.active != null) {
                return false;
            }
        }
        else if(!active.equals(other.active)) {
            return false;
        }
        if(botEnabled == null) {
            if(other.botEnabled != null) {
                return false;
            }
        }
        else if(!botEnabled.equals(other.botEnabled)) {
            return false;
        }
        if(botMmEnabled == null) {
            if(other.botMmEnabled != null) {
                return false;
            }
        }
        else if(!botMmEnabled.equals(other.botMmEnabled)) {
            return false;
        }
        if(champion == null) {
            if(other.champion != null) {
                return false;
            }
        }
        else if(!champion.equals(other.champion)) {
            return false;
        }
        if(freeToPlay == null) {
            if(other.freeToPlay != null) {
                return false;
            }
        }
        else if(!freeToPlay.equals(other.freeToPlay)) {
            return false;
        }
        if(rankedPlayEnabled == null) {
            if(other.rankedPlayEnabled != null) {
                return false;
            }
        }
        else if(!rankedPlayEnabled.equals(other.rankedPlayEnabled)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active == null ? 0 : active.hashCode());
        result = prime * result + (botEnabled == null ? 0 : botEnabled.hashCode());
        result = prime * result + (botMmEnabled == null ? 0 : botMmEnabled.hashCode());
        result = prime * result + (champion == null ? 0 : champion.hashCode());
        result = prime * result + (freeToPlay == null ? 0 : freeToPlay.hashCode());
        result = prime * result + (rankedPlayEnabled == null ? 0 : rankedPlayEnabled.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ChampionStatus [champion=" + champion + "]";
    }
}
