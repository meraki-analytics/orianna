package lib.orianna.type.summoner;

import java.io.Serializable;
import java.util.List;

public class RunePage implements Serializable {
    private static final long serialVersionUID = -1233047466640527128L;
    public final Boolean current;
    public final Long ID;
    public final String name;
    public final List<RuneSlot> slots;

    public RunePage(final Boolean current, final Long ID, final String name, final List<RuneSlot> slots) {
        this.current = current;
        this.ID = ID;
        this.name = name;
        this.slots = slots;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof RunePage)) {
            return false;
        }
        final RunePage other = (RunePage)obj;
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        if(current == null) {
            if(other.current != null) {
                return false;
            }
        }
        else if(!current.equals(other.current)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(slots == null) {
            if(other.slots != null) {
                return false;
            }
        }
        else if(!slots.equals(other.slots)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        result = prime * result + (current == null ? 0 : current.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (slots == null ? 0 : slots.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RunePage [name=" + name + "]";
    }
}
