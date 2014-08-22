package lib.orianna.type.match;

import java.io.Serializable;
import java.util.List;

public class MatchTeam implements Serializable {
    private static final long serialVersionUID = 4302089050149762062L;
    public final List<BannedChampion> bans;
    public final Integer dragonKills, baronKills, inhibitorKills, ID, towerKills, vilemawKills;
    public final Boolean firstBaron, firstBlood, firstDragon, firstInhibitor, firstTower, winner;

    public MatchTeam(final List<BannedChampion> bans, final Integer dragonKills, final Integer baronKills, final Integer inhibitorKills, final Integer ID,
            final Integer towerKills, final Integer vilemawKills, final Boolean firstBaron, final Boolean firstBlood, final Boolean firstDragon,
            final Boolean firstInhibitor, final Boolean firstTower, final Boolean winner) {
        this.bans = bans;
        this.dragonKills = dragonKills;
        this.baronKills = baronKills;
        this.inhibitorKills = inhibitorKills;
        this.ID = ID;
        this.towerKills = towerKills;
        this.vilemawKills = vilemawKills;
        this.firstBaron = firstBaron;
        this.firstBlood = firstBlood;
        this.firstDragon = firstDragon;
        this.firstInhibitor = firstInhibitor;
        this.firstTower = firstTower;
        this.winner = winner;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MatchTeam)) {
            return false;
        }
        final MatchTeam other = (MatchTeam)obj;
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MatchTeam [winner=" + winner + "]";
    }
}
