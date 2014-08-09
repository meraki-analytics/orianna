package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MasteryTree implements Serializable {
    private static final long serialVersionUID = -1878212419731346677L;
    public final List<MasteryTreeList> defense, offense, utility;
    private final Map<Mastery, MasteryType> types;

    public MasteryTree(final List<MasteryTreeList> defense, final List<MasteryTreeList> offense, final List<MasteryTreeList> utility) {
        this.defense = defense;
        this.offense = offense;
        this.utility = utility;
        types = new HashMap<Mastery, MasteryType>();

        offense.forEach((list) -> list.masteryTreeItems.forEach((item) -> {
            if(item != null) {
                types.put(item.mastery, MasteryType.OFFENSE);
            }
        }));
        defense.forEach((list) -> list.masteryTreeItems.forEach((item) -> {
            if(item != null) {
                types.put(item.mastery, MasteryType.DEFENSE);
            }
        }));
        utility.forEach((list) -> list.masteryTreeItems.forEach((item) -> {
            if(item != null) {
                types.put(item.mastery, MasteryType.UTILITY);
            }
        }));
    }

    /**
     * Gets defensive masteries
     *
     * @return defensive masteries
     */
    public List<Mastery> defensiveMasteries() {
        return Collections.unmodifiableList(types.entrySet().stream().filter((entry) -> entry.getValue() == MasteryType.DEFENSE).map((entry) -> entry.getKey())
                .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MasteryTree)) {
            return false;
        }
        final MasteryTree other = (MasteryTree)obj;
        if(defense == null) {
            if(other.defense != null) {
                return false;
            }
        }
        else if(!defense.equals(other.defense)) {
            return false;
        }
        if(offense == null) {
            if(other.offense != null) {
                return false;
            }
        }
        else if(!offense.equals(other.offense)) {
            return false;
        }
        if(utility == null) {
            if(other.utility != null) {
                return false;
            }
        }
        else if(!utility.equals(other.utility)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (defense == null ? 0 : defense.hashCode());
        result = prime * result + (offense == null ? 0 : offense.hashCode());
        result = prime * result + (utility == null ? 0 : utility.hashCode());
        return result;
    }

    /**
     * Gets offensive masteries
     *
     * @return offensive masteries
     */
    public List<Mastery> offensiveMasteries() {
        return Collections.unmodifiableList(types.entrySet().stream().filter((entry) -> entry.getValue() == MasteryType.OFFENSE).map((entry) -> entry.getKey())
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "MasteryTree";
    }

    /**
     * Determines of what type a mastery is (offense, defense, utility)
     *
     * @param mastery
     *            the mastery to test
     * @return the mastery's type
     */
    public MasteryType typeOf(final Mastery mastery) {
        return types.get(mastery);
    }

    /**
     * Gets utility masteries
     *
     * @return utility masteries
     */
    public List<Mastery> utilityMasteries() {
        return Collections.unmodifiableList(types.entrySet().stream().filter((entry) -> entry.getValue() == MasteryType.UTILITY).map((entry) -> entry.getKey())
                .collect(Collectors.toList()));
    }
}
