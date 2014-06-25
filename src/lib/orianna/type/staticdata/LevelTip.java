package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class LevelTip implements Serializable {
    private static final long serialVersionUID = 4561570062132929698L;
    public final List<String> effect, label;

    public LevelTip(final List<String> effect, final List<String> label) {
        this.effect = effect;
        this.label = label;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof LevelTip)) {
            return false;
        }
        final LevelTip other = (LevelTip)obj;
        if(effect == null) {
            if(other.effect != null) {
                return false;
            }
        }
        else if(!effect.equals(other.effect)) {
            return false;
        }
        if(label == null) {
            if(other.label != null) {
                return false;
            }
        }
        else if(!label.equals(other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (effect == null ? 0 : effect.hashCode());
        result = prime * result + (label == null ? 0 : label.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "LevelTip [effect=" + effect + ", label=" + label + "]";
    }
}
