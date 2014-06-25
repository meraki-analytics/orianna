package lib.orianna.type.summoner;

import java.io.Serializable;
import java.util.List;

public class MasteryPage implements Serializable {
    private static final long serialVersionUID = -8858192509700749701L;
    public final Boolean current;
    public final Long ID;
    public final List<MasterySlot> masteries;
    public final String name;

    public MasteryPage(final Boolean current, final Long ID, final List<MasterySlot> masteries, final String name) {
        this.current = current;
        this.ID = ID;
        this.masteries = masteries;
        this.name = name;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MasteryPage)) {
            return false;
        }
        final MasteryPage other = (MasteryPage)obj;
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
        return "MasteryPage [name=" + name + "]";
    }
}
