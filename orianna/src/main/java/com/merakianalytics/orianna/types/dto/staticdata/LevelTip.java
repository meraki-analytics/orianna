package com.merakianalytics.orianna.types.dto.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class LevelTip extends DataObject {
    private static final long serialVersionUID = -375410470788542702L;
    private List<String> effect, label;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final LevelTip other = (LevelTip)obj;
        if(effect == null) {
            if(other.effect != null) {
                return false;
            }
        } else if(!effect.equals(other.effect)) {
            return false;
        }
        if(label == null) {
            if(other.label != null) {
                return false;
            }
        } else if(!label.equals(other.label)) {
            return false;
        }
        return true;
    }

    /**
     * @return the effect
     */
    public List<String> getEffect() {
        return effect;
    }

    /**
     * @return the label
     */
    public List<String> getLabel() {
        return label;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (effect == null ? 0 : effect.hashCode());
        result = prime * result + (label == null ? 0 : label.hashCode());
        return result;
    }

    /**
     * @param effect
     *        the effect to set
     */
    public void setEffect(final List<String> effect) {
        this.effect = effect;
    }

    /**
     * @param label
     *        the label to set
     */
    public void setLabel(final List<String> label) {
        this.label = label;
    }
}
