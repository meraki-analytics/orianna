package com.robrua.orianna.type.core.common;

import java.util.HashMap;
import java.util.Map;

public enum GameMap {
    BUTCHERS_BRIDGE(14), HOWLING_ABYSS(12), SUMMONERS_RIFT(11), SUMMONERS_RIFT_AUTUMN(2), SUMMONERS_RIFT_SUMMER(1), THE_CRYSTAL_SCAR(8), THE_PROVING_GROUNDS(
            3), TWISTED_TREELINE(10), TWISTED_TREELINE_ORIGINAL(4);

    private static final Map<Long, GameMap> IDs = new HashMap<>();

    static {
        for(final GameMap m : GameMap.values()) {
            IDs.put(m.ID, m);
        }
    }

    /**
     * Gets the map for a specified ID
     *
     * @param ID
     *            the map ID
     * @return the map type
     */
    public static GameMap forID(final long ID) {
        return IDs.get(ID);
    }

    private final Long ID;

    /**
     * @param ID
     */
    private GameMap(final long ID) {
        this.ID = ID;
    }

    /**
     * Gets the ID for this map
     *
     * @return the ID for this map
     */
    public long getID() {
        return ID;
    }
}
