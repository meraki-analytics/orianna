package lib.orianna.type.staticdata;

import java.io.Serializable;

public class Skin implements Serializable {
    private static final long serialVersionUID = -7628338896076314314L;
    public final Integer ID, num;
    public final String name;

    public Skin(final Integer ID, final Integer num, final String name) {
        this.ID = ID;
        this.num = num;
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
        if(!(obj instanceof Skin)) {
            return false;
        }
        final Skin other = (Skin)obj;
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
        return name;
    }
}
