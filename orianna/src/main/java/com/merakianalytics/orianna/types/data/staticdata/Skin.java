package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class Skin extends CoreData {
    private static final long serialVersionUID = 9096126189525714311L;
    private String name;
    private int number, id;

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
        final Skin other = (Skin)obj;
        if(id != other.id) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(number != other.number) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + number;
        return result;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param number
     *        the number to set
     */
    public void setNumber(final int number) {
        this.number = number;
    }
}
