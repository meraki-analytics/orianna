package lib.orianna.type.match;

import java.io.Serializable;

import lib.orianna.type.summoner.Summoner;

public class Player implements Serializable {
    private static final long serialVersionUID = -3229640810706125822L;
    public final String matchHistoryURI;
    public final Integer profileIconID;
    public final Summoner summoner;

    public Player(final String matchHistoryURI, final Summoner summoner, final Integer profileIconID) {
        this.matchHistoryURI = matchHistoryURI;
        this.summoner = summoner;
        this.profileIconID = profileIconID;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Player)) {
            return false;
        }
        final Player other = (Player)obj;
        if(summoner == null) {
            if(other.summoner != null) {
                return false;
            }
        }
        else if(!summoner.equals(other.summoner)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (summoner == null ? 0 : summoner.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return summoner.toString();
    }
}
