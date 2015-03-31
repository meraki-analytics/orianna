package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "gold")
public class Gold extends OriannaDto {
    private static final long serialVersionUID = 1419241301361161084L;
    private Integer base, sell, total;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Boolean purchasable;

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

    /**
     * @return the base
     */
    public Integer getBase() {
        return base;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the purchasable
     */
    public Boolean getPurchasable() {
        return purchasable;
    }

    /**
     * @return the sell
     */
    public Integer getSell() {
        return sell;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
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
        result = prime * result + (base == null ? 0 : base.hashCode());
        result = prime * result + (purchasable == null ? 0 : purchasable.hashCode());
        result = prime * result + (sell == null ? 0 : sell.hashCode());
        result = prime * result + (total == null ? 0 : total.hashCode());
        return result;
    }

    /**
     * @param base
     *            the base to set
     */
    public void setBase(final Integer base) {
        this.base = base;
    }

    /**
     * @param purchasable
     *            the purchasable to set
     */
    public void setPurchasable(final Boolean purchasable) {
        this.purchasable = purchasable;
    }

    /**
     * @param sell
     *            the sell to set
     */
    public void setSell(final Integer sell) {
        this.sell = sell;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(final Integer total) {
        this.total = total;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Gold [base=" + base + ", sell=" + sell + ", total=" + total + ", purchasable=" + purchasable + "]";
    }
}
