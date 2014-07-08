package lib.orianna.type.summoner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lib.orianna.type.staticdata.Rune;
import lib.orianna.type.staticdata.RuneType;

public class RuneSlot implements Serializable {
    private static final long serialVersionUID = 2655649709582724355L;
    private static final Map<Integer, RuneType> TYPES = getTypeMap();

    private static Map<Integer, RuneType> getTypeMap() {
        final Map<Integer, RuneType> types = new HashMap<Integer, RuneType>();
        for(int i = 1; i <= 9; i++) {
            types.put(i, RuneType.MARK);
        }
        for(int i = 10; i <= 18; i++) {
            types.put(i, RuneType.SEAL);
        }
        for(int i = 19; i <= 27; i++) {
            types.put(i, RuneType.GLYPH);
        }
        for(int i = 28; i <= 30; i++) {
            types.put(i, RuneType.QUINTESSENCE);
        }

        return types;
    }

    public final Integer ID;
    public final Rune rune;
    public final RuneType type;

    public RuneSlot(final Integer ID, final Rune rune) {
        this.ID = ID;
        this.rune = rune;
        type = TYPES.get(ID);
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
