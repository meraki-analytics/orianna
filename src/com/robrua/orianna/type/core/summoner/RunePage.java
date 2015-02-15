package com.robrua.orianna.type.core.summoner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class RunePage extends OriannaObject<com.robrua.orianna.type.dto.summoner.RunePage> {
    private static final long serialVersionUID = -2950195889947875600L;
    private List<RuneSlot> slots;

    /**
     * @param data
     *            the underlying dto
     */
    public RunePage(final com.robrua.orianna.type.dto.summoner.RunePage data) {
        super(data, com.robrua.orianna.type.dto.summoner.RunePage.class);
    }

    /**
     * Indicates if the page is the current page
     *
     * @return indicates if the page is the current page
     */
    public boolean getCurrent() {
        return super.getBoolean(data.getCurrent());
    }

    /**
     * Rune page ID
     *
     * @return rune page ID
     */
    public long getID() {
        return super.getLong(data.getId());
    }

    /**
     * Rune page name
     *
     * @return rune page name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * Collection of rune slots associated with the rune page
     *
     * @return collection of rune slots associated with the rune page
     */
    public List<RuneSlot> getSlots() {
        if(slots == null) {
            slots = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.summoner.RuneSlot slot : data.getSlots()) {
                slots.add(new RuneSlot(slot));
            }
        }

        return Collections.unmodifiableList(slots);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RunePage (" + getName() + ")";
    }
}
