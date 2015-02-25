package com.robrua.orianna.type.core;

import java.io.Serializable;
import java.util.Date;

import com.robrua.orianna.type.dto.OriannaDto;
import com.robrua.orianna.type.exception.OriannaException;

public abstract class OriannaObject<T extends OriannaDto> implements Serializable {
    private static final long serialVersionUID = 5087357430758694870L;
    protected T data;

    /**
     * @param data
     *            the underlying dto
     * @param clazz
     *            the class of the underlying dto
     */
    public OriannaObject(final T data, final Class<T> clazz) {
        if(data == null) {
            try {
                this.data = clazz.newInstance();
            }
            catch(final Exception e) {
                throw new OriannaException(e.getMessage());
            }
        }
        else {
            this.data = data;
        }
    }

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
        if(!(obj instanceof OriannaObject)) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        final OriannaObject other = (OriannaObject)obj;
        if(data == null) {
            if(other.data != null) {
                return false;
            }
        }
        else if(!data.equals(other.data)) {
            return false;
        }
        return true;
    }

    /**
     * Ensures proper default boolean values for unsent data
     *
     * @param b
     *            the boolean
     * @return the boolean or false if null
     */
    protected boolean getBoolean(final Boolean b) {
        return b == null ? false : b.booleanValue();
    }

    /**
     * Ensures proper default Date values for unsent data
     *
     * @param l
     *            the date (epoch timestamp)
     * @return the Date or null if null
     */
    protected Date getDate(final Long l) {
        return l == null ? null : new Date(l.longValue());
    }

    /**
     * Ensures proper default double values for unsent data
     *
     * @param d
     *            the double
     * @return the double or 0.0 if null
     */
    protected double getDouble(final Double d) {
        return d == null ? 0.0 : d.doubleValue();
    }

    /**
     * @return the underlying dto
     */
    public T getDto() {
        return data;
    }

    /**
     * Ensures proper default int values for unsent data
     *
     * @param i
     *            the integer
     * @return the int or 0 if null
     */
    protected int getInteger(final Integer i) {
        return i == null ? 0 : i.intValue();
    }

    /**
     * Ensures proper default long values for unsent data
     *
     * @param l
     *            the long
     * @return the long or 0 if null
     */
    protected long getLong(final Long l) {
        return l == null ? 0 : l.longValue();
    }

    /**
     * Ensures proper default String values for unsent data
     *
     * @param s
     *            the String
     * @return the String or "" if null
     */
    protected String getString(final String s) {
        return s == null ? "" : s;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (data == null ? 0 : data.hashCode());
        return result;
    }
}
