package com.robrua.orianna.type.match;

import java.io.Serializable;
import java.util.List;

public class MatchTeam implements Serializable {
    private static final long serialVersionUID = 3000506179224128304L;
    public final List<BannedChampion> bans;
    public final Integer dragonKills, baronKills, inhibitorKills, towerKills, vilemawKills;
    public final Boolean firstBaron, firstBlood, firstDragon, firstInhibitor, firstTower, winner;
    public final Side side;

    public MatchTeam(final List<BannedChampion> bans, final Integer dragonKills, final Integer baronKills, final Integer inhibitorKills,
            final Integer towerKills, final Integer vilemawKills, final Boolean firstBaron, final Boolean firstBlood, final Boolean firstDragon,
            final Boolean firstInhibitor, final Boolean firstTower, final Boolean winner, final Side side) {
        this.bans = bans;
        this.dragonKills = dragonKills;
        this.baronKills = baronKills;
        this.inhibitorKills = inhibitorKills;
        this.towerKills = towerKills;
        this.vilemawKills = vilemawKills;
        this.firstBaron = firstBaron;
        this.firstBlood = firstBlood;
        this.firstDragon = firstDragon;
        this.firstInhibitor = firstInhibitor;
        this.firstTower = firstTower;
        this.winner = winner;
        this.side = side;
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
        if(bans == null) {
            if(other.bans != null) {
                return false;
            }
        }
        else if(!bans.equals(other.bans)) {
            return false;
        }
        if(baronKills == null) {
            if(other.baronKills != null) {
                return false;
            }
        }
        else if(!baronKills.equals(other.baronKills)) {
            return false;
        }
        if(dragonKills == null) {
            if(other.dragonKills != null) {
                return false;
            }
        }
        else if(!dragonKills.equals(other.dragonKills)) {
            return false;
        }
        if(firstBaron == null) {
            if(other.firstBaron != null) {
                return false;
            }
        }
        else if(!firstBaron.equals(other.firstBaron)) {
            return false;
        }
        if(firstBlood == null) {
            if(other.firstBlood != null) {
                return false;
            }
        }
        else if(!firstBlood.equals(other.firstBlood)) {
            return false;
        }
        if(firstDragon == null) {
            if(other.firstDragon != null) {
                return false;
            }
        }
        else if(!firstDragon.equals(other.firstDragon)) {
            return false;
        }
        if(firstInhibitor == null) {
            if(other.firstInhibitor != null) {
                return false;
            }
        }
        else if(!firstInhibitor.equals(other.firstInhibitor)) {
            return false;
        }
        if(firstTower == null) {
            if(other.firstTower != null) {
                return false;
            }
        }
        else if(!firstTower.equals(other.firstTower)) {
            return false;
        }
        if(inhibitorKills == null) {
            if(other.inhibitorKills != null) {
                return false;
            }
        }
        else if(!inhibitorKills.equals(other.inhibitorKills)) {
            return false;
        }
        if(side != other.side) {
            return false;
        }
        if(towerKills == null) {
            if(other.towerKills != null) {
                return false;
            }
        }
        else if(!towerKills.equals(other.towerKills)) {
            return false;
        }
        if(vilemawKills == null) {
            if(other.vilemawKills != null) {
                return false;
            }
        }
        else if(!vilemawKills.equals(other.vilemawKills)) {
            return false;
        }
        if(winner == null) {
            if(other.winner != null) {
                return false;
            }
        }
        else if(!winner.equals(other.winner)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bans == null ? 0 : bans.hashCode());
        result = prime * result + (baronKills == null ? 0 : baronKills.hashCode());
        result = prime * result + (dragonKills == null ? 0 : dragonKills.hashCode());
        result = prime * result + (firstBaron == null ? 0 : firstBaron.hashCode());
        result = prime * result + (firstBlood == null ? 0 : firstBlood.hashCode());
        result = prime * result + (firstDragon == null ? 0 : firstDragon.hashCode());
        result = prime * result + (firstInhibitor == null ? 0 : firstInhibitor.hashCode());
        result = prime * result + (firstTower == null ? 0 : firstTower.hashCode());
        result = prime * result + (inhibitorKills == null ? 0 : inhibitorKills.hashCode());
        result = prime * result + (side == null ? 0 : side.hashCode());
        result = prime * result + (towerKills == null ? 0 : towerKills.hashCode());
        result = prime * result + (vilemawKills == null ? 0 : vilemawKills.hashCode());
        result = prime * result + (winner == null ? 0 : winner.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MatchTeam [side=" + side + "]";
    }
}
