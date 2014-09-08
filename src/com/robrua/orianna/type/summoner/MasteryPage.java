package com.robrua.orianna.type.summoner;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.robrua.orianna.type.staticdata.MasteryType;

public class MasteryPage implements Serializable {
    private static final long serialVersionUID = -7504309025175797747L;
    public final Boolean current;
    public final Long ID;
    public final List<MasterySlot> masteries;
    public final String name;

    public MasteryPage(final Boolean current, final Long ID, final List<MasterySlot> masteries, final String name) {
        this.current = current;
        this.ID = ID;
        this.masteries = masteries;
        this.name = name;
    }

    /**
     * Gets only the defensive slots
     *
     * @return the defense slots
     */
    public List<MasterySlot> defenseSlots() {
        return Collections.unmodifiableList(masteries.stream().filter((slot) -> slot.type == MasteryType.DEFENSE).collect(Collectors.toList()));
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MasteryPage)) {
            return false;
        }
        final MasteryPage other = (MasteryPage)obj;
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
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        }
        else if(!masteries.equals(other.masteries)) {
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
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        result = prime * result + (current == null ? 0 : current.hashCode());
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    /**
     * Gets only the offensive slots
     *
     * @return the offense slots
     */
    public List<MasterySlot> offenseSlots() {
        return Collections.unmodifiableList(masteries.stream().filter((slot) -> slot.type == MasteryType.OFFENSE).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "MasteryPage [name=" + name + "]";
    }

    /**
     * Gets only the utility slots
     *
     * @return the utility slots
     */
    public List<MasterySlot> utilitySlots() {
        return Collections.unmodifiableList(masteries.stream().filter((slot) -> slot.type == MasteryType.UTILITY).collect(Collectors.toList()));
    }
}
