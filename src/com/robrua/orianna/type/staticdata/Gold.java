package com.robrua.orianna.type.staticdata;

import java.io.Serializable;

public class Gold implements Serializable {
    private static final long serialVersionUID = -6638499985237258390L;
    public final Integer base, sell, total;
    public final Boolean purchasable;

    public Gold(final Integer base, final Integer sell, final Integer total, final Boolean purchasable) {
        this.base = base;
        this.sell = sell;
        this.total = total;
        this.purchasable = purchasable;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Gold)) {
            return false;
        }
        final Gold other = (Gold)obj;
        if(base == null) {
            if(other.base != null) {
                return false;
            }
        }
        else if(!base.equals(other.base)) {
            return false;
        }
        if(purchasable == null) {
            if(other.purchasable != null) {
                return false;
            }
        }
        else if(!purchasable.equals(other.purchasable)) {
            return false;
        }
        if(sell == null) {
            if(other.sell != null) {
                return false;
            }
        }
        else if(!sell.equals(other.sell)) {
            return false;
        }
        if(total == null) {
            if(other.total != null) {
                return false;
            }
        }
        else if(!total.equals(other.total)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (base == null ? 0 : base.hashCode());
        result = prime * result + (purchasable == null ? 0 : purchasable.hashCode());
        result = prime * result + (sell == null ? 0 : sell.hashCode());
        result = prime * result + (total == null ? 0 : total.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Gold [base=" + base + ", sell=" + sell + ", total=" + total + ", purchasable=" + purchasable + "]";
    }
}
