package com.robrua.orianna.type.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParamsBuilder {
    private final Map<String, String> map;

    /**
     *
     */
    public ParamsBuilder() {
        map = new HashMap<>();
    }

    /**
     * Adds a parameter
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder add(final String key, final boolean value) {
        map.put(key, Boolean.toString(value));
        return this;
    }

    /**
     * Adds a parameter
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder add(final String key, final Collection<?> value) {
        final StringBuilder sb = new StringBuilder();
        for(final Object o : value) {
            sb.append("," + o.toString());
        }
        map.put(key, sb.substring(1));
        return this;
    }

    /**
     * Adds a parameter
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder add(final String key, final double value) {
        map.put(key, Double.toString(value));
        return this;
    }

    /**
     * Adds a parameter
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder add(final String key, final int value) {
        map.put(key, Integer.toString(value));
        return this;
    }

    /**
     * Adds a parameter
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder add(final String key, final long value) {
        map.put(key, Long.toString(value));
        return this;
    }

    /**
     * Adds a parameter
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder add(final String key, final Object value) {
        map.put(key, value.toString());
        return this;
    }

    /**
     * Adds a parameter if the value is not null
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder addIfNotNull(final String key, final Collection<?> value) {
        if(value != null) {
            final StringBuilder sb = new StringBuilder();
            for(final Object o : value) {
                sb.append("," + o.toString());
            }
            map.put(key, sb.substring(1));
        }
        return this;
    }

    /**
     * Adds a parameter if the value is not null
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @return this
     */
    public ParamsBuilder addIfNotNull(final String key, final Object value) {
        if(value != null) {
            map.put(key, value.toString());
        }
        return this;
    }

    /**
     * Gets the parameter map
     *
     * @return the parameter map
     */
    public Map<String, String> build() {
        return map;
    }
}
