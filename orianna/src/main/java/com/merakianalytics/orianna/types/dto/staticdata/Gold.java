package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Gold extends DataObject {
    private static final long serialVersionUID = 6907973454310387830L;
    private boolean purchasable;
    private int sell, total, base;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        final Gold other = (Gold)obj;
        if(base != other.base) {
            return false;
        }
        if(purchasable != other.purchasable) {
            return false;
        }
        if(sell != other.sell) {
            return false;
        }
        if(total != other.total) {
            return false;
        }
        return true;
    }

    /**
     * @return the base
     */
    public int getBase() {
        return base;
    }

    /**
     * @return the sell
     */
    public int getSell() {
        return sell;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + base;
        result = prime * result + (purchasable ? 1231 : 1237);
        result = prime * result + sell;
        result = prime * result + total;
        return result;
    }

    /**
     * @return the purchasable
     */
    public boolean isPurchasable() {
        return purchasable;
    }

    /**
     * @param base
     *        the base to set
     */
    public void setBase(final int base) {
        this.base = base;
    }

    /**
     * @param purchasable
     *        the purchasable to set
     */
    public void setPurchasable(final boolean purchasable) {
        this.purchasable = purchasable;
    }

    /**
     * @param sell
     *        the sell to set
     */
    public void setSell(final int sell) {
        this.sell = sell;
    }

    /**
     * @param total
     *        the total to set
     */
    public void setTotal(final int total) {
        this.total = total;
    }
}
