package com.merakianalytics.orianna.types.common;

import java.util.Comparator;

public enum Tier {
    UNRANKED(0),
    IRON(1),
    BRONZE(2),
    SILVER(3),
    GOLD(4),
    PLATINUM(5),
    EMERALD(6),
    DIAMOND(7),
    MASTER(8),
    GRANDMASTER(9),
    CHALLENGER(10);

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

    public int getLevel() {
        return level;
    }
}
