package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class SpellVariables implements Serializable {
    private static final long serialVersionUID = 6252805997289142429L;
    public final List<Double> coeff;
    public final String dyn, key, link, ranksWith;

    public SpellVariables(final String dyn, final String key, final String link, final String ranksWith, final List<Double> coeff) {
        this.dyn = dyn;
        this.key = key;
        this.link = link;
        this.ranksWith = ranksWith;
        this.coeff = coeff;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof SpellVariables)) {
            return false;
        }
        final SpellVariables other = (SpellVariables)obj;
        if(coeff == null) {
            if(other.coeff != null) {
                return false;
            }
        }
        else if(!coeff.equals(other.coeff)) {
            return false;
        }
        if(dyn == null) {
            if(other.dyn != null) {
                return false;
            }
        }
        else if(!dyn.equals(other.dyn)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        }
        else if(!key.equals(other.key)) {
            return false;
        }
        if(link == null) {
            if(other.link != null) {
                return false;
            }
        }
        else if(!link.equals(other.link)) {
            return false;
        }
        if(ranksWith == null) {
            if(other.ranksWith != null) {
                return false;
            }
        }
        else if(!ranksWith.equals(other.ranksWith)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (coeff == null ? 0 : coeff.hashCode());
        result = prime * result + (dyn == null ? 0 : dyn.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (link == null ? 0 : link.hashCode());
        result = prime * result + (ranksWith == null ? 0 : ranksWith.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SpellVariables [dyn=" + dyn + ", key=" + key + ", link=" + link + ", ranksWith=" + ranksWith + ", coeff=" + coeff + "]";
    }
}
