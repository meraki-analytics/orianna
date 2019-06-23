package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum Season {
        PRE_SEASON_3(0),
        PRE_SEASON_4(2),
        PRE_SEASON_5(4),
        PRE_SEASON_6(6),
        PRE_SEASON_7(8),
        PRE_SEASON_8(10),
        PRE_SEASON_9(12),
        SEASON_3(1),
        SEASON_4(3),
        SEASON_5(5),
        SEASON_6(7),
        SEASON_7(9),
        SEASON_8(11),
        SEASON_9(13);

    private static final java.util.Map<Integer, Season> BY_ID = getById();

    private static final java.util.Map<Integer, Season> getById() {
        final Builder<Integer, Season> builder = ImmutableMap.builder();
        for(final Season season : values()) {
            builder.put(season.id, season);
        }
        return builder.build();
    }

    public static Season getLatest() {
        Season latest = null;
        for(final Season season : Season.values()) {
            if(latest == null || season.getId() > latest.getId()) {
                latest = season;
            }
        }
        return latest;
    }

    public static Season withId(final int id) {
        return BY_ID.get(id);
    }

    private final int id;

    private Season(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
