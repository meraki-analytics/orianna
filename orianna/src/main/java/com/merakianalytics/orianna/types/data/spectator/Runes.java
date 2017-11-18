package com.merakianalytics.orianna.types.data.spectator;

import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.data.CoreData;

public class Runes extends CoreData.ListProxy<Integer> {
    private static final long serialVersionUID = 7169291121901035271L;
    private RunePath primaryPath, secondaryPath;

    public Runes() {
        super();
    }

    public Runes(final int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(!super.equals(obj)) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Runes other = (Runes)obj;
        if(primaryPath != other.primaryPath) {
            return false;
        }
        if(secondaryPath != other.secondaryPath) {
            return false;
        }
        return true;
    }

    /**
     * @return the primaryPath
     */
    public RunePath getPrimaryPath() {
        return primaryPath;
    }

    /**
     * @return the secondaryPath
     */
    public RunePath getSecondaryPath() {
        return secondaryPath;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (primaryPath == null ? 0 : primaryPath.hashCode());
        result = prime * result + (secondaryPath == null ? 0 : secondaryPath.hashCode());
        return result;
    }

    /**
     * @param primaryPath
     *        the primaryPath to set
     */
    public void setPrimaryPath(final RunePath primaryPath) {
        this.primaryPath = primaryPath;
    }

    /**
     * @param secondaryPath
     *        the secondaryPath to set
     */
    public void setSecondaryPath(final RunePath secondaryPath) {
        this.secondaryPath = secondaryPath;
    }
}
