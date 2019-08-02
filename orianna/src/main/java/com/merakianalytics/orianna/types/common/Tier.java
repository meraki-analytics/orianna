package com.merakianalytics.orianna.types.common;

import java.util.Comparator;

public enum Tier {
        BRONZE(1),
        CHALLENGER(8),
        DIAMOND(5),
        GOLD(3),
        GRANDMASTER(7),
        MASTER(6),
        PLATINUM(4),
        SILVER(2),
        UNRANKED(0);

    public static Comparator<Tier> getComparator() {
        return new Comparator<Tier>() {
            @Override
            public int compare(final Tier o1, final Tier o2) {
                return Integer.compare(o1.level, o2.level);
            }
        };
    }

    private final int level;

    private Tier(final int level) {
        this.level = level;
    }

    public int compare(final Tier o) {
        return Integer.compare(level, o.level);
    }
}
