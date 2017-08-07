package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum Skill {
    E(3),
    Q(1),
    R(4),
    W(2);

    private static final java.util.Map<Integer, Skill> BY_SLOT = getBySlot();

    private static final java.util.Map<Integer, Skill> getBySlot() {
        final Builder<Integer, Skill> builder = ImmutableMap.builder();
        for(final Skill skill : values()) {
            builder.put(skill.slot, skill);
        }
        return builder.build();
    }

    public static Skill withId(final int id) {
        return BY_SLOT.get(id);
    }

    private final int slot;

    private Skill(final int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
