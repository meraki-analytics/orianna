package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum Map {
        BUTCHERS_BRIDGE(14),
        COSMIC_RUINS(16),
        HOWLING_ABYSS(12),
        SUBSTRUCTURE_43(19),
        SUMMONERS_RIFT(11),
        SUMMONERS_RIFT_AUTUMN(2),
        SUMMONERS_RIFT_SUMMER(1),
        THE_CRYSTAL_SCAR(8),
        THE_PROVING_GROUNDS(3),
        TWISTED_TREELINE(10),
        TWISTED_TREELINE_LEGACY(4),
        VALORAN_CITY_PARK(18);

    private static final java.util.Map<Integer, Map> BY_ID = getById();

    private static final java.util.Map<Integer, Map> getById() {
        final Builder<Integer, Map> builder = ImmutableMap.builder();
        for(final Map map : values()) {
            builder.put(map.id, map);
        }
        return builder.build();
    }

    public static Map withId(final int id) {
        return BY_ID.get(id);
    }

    private final int id;

    private Map(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
