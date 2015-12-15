package com.robrua.orianna.type.core.common;

import java.util.HashMap;
import java.util.Map;

public enum Side {
    BLUE(100), PURPLE(200);

    private static final Map<Long, Side> IDs = new HashMap<>();

    static {
        for(final Side s : Side.values()) {
            IDs.put(s.ID, s);
        }
    }

    /**
     * Gets the side a specified ID
     *
     * @param ID
     *            the side ID
     * @return the side
     */
    public static Side forID(final long ID) {
        return IDs.get(ID);
    }

    private final Long ID;

    /**
     * @param ID
     */
    private Side(final long ID) {
        this.ID = ID;
    }

    /**
     * Gets the ID for this side
     *
     * @return the ID for this side
     */
    public long getID() {
        return ID;
    }
}
