package com.merakianalytics.orianna.types.data.match;

import com.merakianalytics.orianna.types.data.CoreData;

public class StatTotals extends CoreData {
    private static final long serialVersionUID = 8972227385369583903L;
    private double at10, at20, at30, atGameEnd;

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final StatTotals other = (StatTotals)obj;
        if(Double.doubleToLongBits(at10) != Double.doubleToLongBits(other.at10)) {
            return false;
        }
        if(Double.doubleToLongBits(at20) != Double.doubleToLongBits(other.at20)) {
            return false;
        }
        if(Double.doubleToLongBits(at30) != Double.doubleToLongBits(other.at30)) {
            return false;
        }
        if(Double.doubleToLongBits(atGameEnd) != Double.doubleToLongBits(other.atGameEnd)) {
            return false;
        }
        return true;
    }

    /**
     * @return the at10
     */
    public double getAt10() {
        return at10;
    }

    /**
     * @return the at20
     */
    public double getAt20() {
        return at20;
    }

    /**
     * @return the at30
     */
    public double getAt30() {
        return at30;
    }

    /**
     * @return the atGameEnd
     */
    public double getAtGameEnd() {
        return atGameEnd;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(at10);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(at20);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(at30);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(atGameEnd);
        result = prime * result + (int)(temp ^ temp >>> 32);
        return result;
    }

    /**
     * @param at10
     *        the at10 to set
     */
    public void setAt10(final double at10) {
        this.at10 = at10;
    }

    /**
     * @param at20
     *        the at20 to set
     */
    public void setAt20(final double at20) {
        this.at20 = at20;
    }

    /**
     * @param at30
     *        the at30 to set
     */
    public void setAt30(final double at30) {
        this.at30 = at30;
    }

    /**
     * @param atGameEnd
     *        the atGameEnd to set
     */
    public void setAtGameEnd(final double atGameEnd) {
        this.atGameEnd = atGameEnd;
    }
}
