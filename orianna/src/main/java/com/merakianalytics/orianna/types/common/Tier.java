package com.merakianalytics.orianna.types.common;

import java.util.Comparator;

public enum Tier {
        BRONZE(2),
        CHALLENGER(9),
        DIAMOND(6),
        GOLD(4),
        GRANDMASTER(8),
        IRON(1),
        MASTER(7),
        PLATINUM(5),
        SILVER(3),
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
