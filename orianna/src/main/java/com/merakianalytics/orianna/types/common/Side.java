package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum Side {
        BLUE(100),
        RED(200);

    private static final java.util.Map<Integer, Side> BY_ID = getById();

    private static final java.util.Map<Integer, Side> getById() {
        final Builder<Integer, Side> builder = ImmutableMap.builder();
        for(final Side team : values()) {
            builder.put(team.id, team);
        }
        return builder.build();
    }

    public static Side withId(final int id) {
        return BY_ID.get(id);
    }

    private final int id;

    private Side(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
