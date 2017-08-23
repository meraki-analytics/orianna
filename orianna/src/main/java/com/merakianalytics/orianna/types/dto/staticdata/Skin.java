package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Skin extends DataObject {
    private static final long serialVersionUID = 1324299868449271270L;
    private String name;
    private int num, id;

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
        if(num != other.num) {
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
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + num;
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
     * @param num
     *        the num to set
     */
    public void setNum(final int num) {
        this.num = num;
    }
}
