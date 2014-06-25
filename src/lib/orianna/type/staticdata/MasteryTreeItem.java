package lib.orianna.type.staticdata;

import java.io.Serializable;

public class MasteryTreeItem implements Serializable {
    private static final long serialVersionUID = 4177984908044478410L;
    public final Mastery mastery;
    public final String prereq;

    public MasteryTreeItem(final Mastery mastery, final String prereq) {
        this.mastery = mastery;
        this.prereq = prereq;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MasteryTreeItem)) {
            return false;
        }
        final MasteryTreeItem other = (MasteryTreeItem)obj;
        if(mastery == null) {
            if(other.mastery != null) {
                return false;
            }
        }
        else if(!mastery.equals(other.mastery)) {
            return false;
        }
        if(prereq == null) {
            if(other.prereq != null) {
                return false;
            }
        }
        else if(!prereq.equals(other.prereq)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (mastery == null ? 0 : mastery.hashCode());
        result = prime * result + (prereq == null ? 0 : prereq.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MasteryTreeItem [mastery=" + mastery + ", prereq=" + prereq + "]";
    }
}
