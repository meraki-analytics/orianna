package lib.orianna.type.staticdata;

import java.io.Serializable;

public class MetaData implements Serializable {
    private static final long serialVersionUID = -3481055934576447450L;
    public final Boolean isRune;
    public final String tier;
    public final RuneType type;

    public MetaData(final Boolean isRune, final String tier, final RuneType type) {
        this.isRune = isRune;
        this.tier = tier;
        this.type = type;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MetaData)) {
            return false;
        }
        final MetaData other = (MetaData)obj;
        if(isRune == null) {
            if(other.isRune != null) {
                return false;
            }
        }
        else if(!isRune.equals(other.isRune)) {
            return false;
        }
        if(tier == null) {
            if(other.tier != null) {
                return false;
            }
        }
        else if(!tier.equals(other.tier)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        }
        else if(!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isRune == null ? 0 : isRune.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MetaData [isRune=" + isRune + ", tier=" + tier + ", type=" + type + "]";
    }
}
