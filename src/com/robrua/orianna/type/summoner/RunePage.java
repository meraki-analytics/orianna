package com.robrua.orianna.type.summoner;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.robrua.orianna.type.staticdata.RuneType;

public class RunePage implements Serializable {
    private static final long serialVersionUID = 3237362434900206119L;
    public final Boolean current;
    public final Long ID;
    public final String name;
    public final List<RuneSlot> slots;

    public RunePage(final Boolean current, final Long ID, final String name, final List<RuneSlot> slots) {
        this.current = current;
        this.ID = ID;
        this.name = name;
        this.slots = slots;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof RunePage)) {
            return false;
        }
        final RunePage other = (RunePage)obj;
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        if(current == null) {
            if(other.current != null) {
                return false;
            }
        }
        else if(!current.equals(other.current)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(slots == null) {
            if(other.slots != null) {
                return false;
            }
        }
        else if(!slots.equals(other.slots)) {
            return false;
        }
        return true;
    }

    /**
     * Gets only the blue rune slots
     *
     * @return the blue rune slots
     */
    public List<RuneSlot> glyphSlots() {
        return Collections.unmodifiableList(slots.stream().filter((slot) -> slot.type == RuneType.GLYPH).collect(Collectors.toList()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        result = prime * result + (current == null ? 0 : current.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (slots == null ? 0 : slots.hashCode());
        return result;
    }

    /**
     * Gets only the red rune slots
     *
     * @return the red rune slots
     */
    public List<RuneSlot> markSlots() {
        return Collections.unmodifiableList(slots.stream().filter((slot) -> slot.type == RuneType.MARK).collect(Collectors.toList()));
    }

    /**
     * Gets only the quintessence rune slots
     *
     * @return the quintessence rune slots
     */
    public List<RuneSlot> quintessenceSlots() {
        return Collections.unmodifiableList(slots.stream().filter((slot) -> slot.type == RuneType.QUINTESSENCE).collect(Collectors.toList()));
    }

    /**
     * Gets only the yellow rune slots
     *
     * @return the yellow rune slots
     */
    public List<RuneSlot> sealSlots() {
        return Collections.unmodifiableList(slots.stream().filter((slot) -> slot.type == RuneType.SEAL).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "RunePage [name=" + name + "]";
    }
}
