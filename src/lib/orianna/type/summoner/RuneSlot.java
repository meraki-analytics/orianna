package lib.orianna.type.summoner;

import java.io.Serializable;

import lib.orianna.type.staticdata.Rune;

public class RuneSlot implements Serializable {
    private static final long serialVersionUID = -6140176326830028668L;
    public final Integer ID;
    public final Rune rune;

    public RuneSlot(final Integer ID, final Rune rune) {
        this.ID = ID;
        this.rune = rune;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof RuneSlot)) {
            return false;
        }
        final RuneSlot other = (RuneSlot)obj;
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        if(rune == null) {
            if(other.rune != null) {
                return false;
            }
        }
        else if(!rune.equals(other.rune)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        result = prime * result + (rune == null ? 0 : rune.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RuneSlot [rune=" + rune + "]";
    }
}
