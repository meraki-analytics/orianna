package com.robrua.orianna.type.api;

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
     */
    public ParamsBuilder add(final String key, final Object value) {
        map.put(key, value.toString());
        return this;
    }

    /**
     * Gets the parameter map
     *
     * @return
     */
    public Map<String, String> build() {
        return map;
    }
}
