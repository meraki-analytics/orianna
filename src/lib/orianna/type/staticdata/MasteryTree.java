package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class MasteryTree implements Serializable {
    private static final long serialVersionUID = 3797413511452563457L;
    public final List<MasteryTreeList> defense, offense, utility;

    public MasteryTree(final List<MasteryTreeList> defense, final List<MasteryTreeList> offense, final List<MasteryTreeList> utility) {
        this.defense = defense;
        this.offense = offense;
        this.utility = utility;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MasteryTree)) {
            return false;
        }
        final MasteryTree other = (MasteryTree)obj;
        if(defense == null) {
            if(other.defense != null) {
                return false;
            }
        }
        else if(!defense.equals(other.defense)) {
            return false;
        }
        if(offense == null) {
            if(other.offense != null) {
                return false;
            }
        }
        else if(!offense.equals(other.offense)) {
            return false;
        }
        if(utility == null) {
            if(other.utility != null) {
                return false;
            }
        }
        else if(!utility.equals(other.utility)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (defense == null ? 0 : defense.hashCode());
        result = prime * result + (offense == null ? 0 : offense.hashCode());
        result = prime * result + (utility == null ? 0 : utility.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MasteryTree";
    }
}
