package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum Team {
        BLUE(100),
        RED(200);

    private static final java.util.Map<Integer, Team> BY_ID = getById();

    private static final java.util.Map<Integer, Team> getById() {
        final Builder<Integer, Team> builder = ImmutableMap.builder();
        for(final Team team : values()) {
            builder.put(team.id, team);
        }
        return builder.build();
    }

    public static Team withId(final int id) {
        return BY_ID.get(id);
    }

    private final int id;

    private Team(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
