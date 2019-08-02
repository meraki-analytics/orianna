package com.merakianalytics.orianna.types.common;

import java.util.Comparator;

public enum Division {
        I(4),
        II(3),
        III(2),
        IV(1),
        V(0);

    public static Comparator<Division> getComparator() {
        return new Comparator<Division>() {
            @Override
            public int compare(final Division o1, final Division o2) {
                return Integer.compare(o1.level, o2.level);
            }
        };
    }

    private final int level;

    private Division(final int level) {
        this.level = level;
    }

    public int compare(final Division o) {
        return Integer.compare(level, o.level);
    }
}
