package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class LevelTip extends OriannaObject<com.robrua.orianna.type.dto.staticdata.LevelTip> {
    private static final long serialVersionUID = -4507146326341310133L;
    private List<String> effect, label;

    /**
     * @param data
     *            the underlying dto
     */
    public LevelTip(final com.robrua.orianna.type.dto.staticdata.LevelTip data) {
        super(data, com.robrua.orianna.type.dto.staticdata.LevelTip.class);
    }

    /**
     * Effect
     *
     * @return effect
     */
    public List<String> getEffect() {
        if(effect == null) {
            effect = new ArrayList<>();
            effect.addAll(data.getEffect());
        }

        return Collections.unmodifiableList(effect);
    }

    /**
     * Label
     *
     * @return label
     */
    public List<String> getLabel() {
        if(label == null) {
            label = new ArrayList<>();
            label.addAll(data.getLabel());
        }

        return Collections.unmodifiableList(label);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LevelTip";
    }
}
