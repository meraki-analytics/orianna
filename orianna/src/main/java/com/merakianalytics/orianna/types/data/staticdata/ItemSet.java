package com.merakianalytics.orianna.types.data.staticdata;

import java.util.Map;

import com.merakianalytics.orianna.types.data.CoreData;

public class ItemSet extends CoreData {
    private static final long serialVersionUID = -3457297676429499262L;
    private Map<Integer, Integer> items;
    private boolean recMath;
    private String type;

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
        final ItemSet other = (ItemSet)obj;
        if(items == null) {
            if(other.items != null) {
                return false;
            }
        } else if(!items.equals(other.items)) {
            return false;
        }
        if(recMath != other.recMath) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        } else if(!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    /**
     * @return the items
     */
    public Map<Integer, Integer> getItems() {
        return items;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (items == null ? 0 : items.hashCode());
        result = prime * result + (recMath ? 1231 : 1237);
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @return the recMath
     */
    public boolean isRecMath() {
        return recMath;
    }

    /**
     * @param items
     *        the items to set
     */
    public void setItems(final Map<Integer, Integer> items) {
        this.items = items;
    }

    /**
     * @param recMath
     *        the recMath to set
     */
    public void setRecMath(final boolean recMath) {
        this.recMath = recMath;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }
}
