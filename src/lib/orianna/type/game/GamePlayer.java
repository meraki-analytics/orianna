package lib.orianna.type.game;

import java.io.Serializable;

import lib.orianna.type.match.Side;
import lib.orianna.type.staticdata.Champion;
import lib.orianna.type.summoner.Summoner;

public class GamePlayer implements Serializable {
    private static final long serialVersionUID = -4282238671983411505L;
    public final Champion champion;
    public final Summoner summoner;
    public final Side team;

    public GamePlayer(final Champion champion, final Summoner summoner, final Side team) {
        this.champion = champion;
        this.summoner = summoner;
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
        if(!(obj instanceof GamePlayer)) {
            return false;
        }
        final GamePlayer other = (GamePlayer)obj;
        if(champion == null) {
            if(other.champion != null) {
                return false;
            }
        }
        else if(!champion.equals(other.champion)) {
            return false;
        }
        if(summoner == null) {
            if(other.summoner != null) {
                return false;
            }
        }
        else if(!summoner.equals(other.summoner)) {
            return false;
        }
        if(team != other.team) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (champion == null ? 0 : champion.hashCode());
        result = prime * result + (summoner == null ? 0 : summoner.hashCode());
        result = prime * result + (team == null ? 0 : team.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return summoner + " [" + champion + "]";
    }
}
