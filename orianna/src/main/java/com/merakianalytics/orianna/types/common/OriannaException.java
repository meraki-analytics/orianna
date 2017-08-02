package com.merakianalytics.orianna.types.common;

public class OriannaException extends RuntimeException {
    private static final long serialVersionUID = 1737866138838972345L;

    public OriannaException(final String message) {
        super(message);
    }

    public OriannaException(final String message, final Throwable source) {
        super(message, source);
    }
}
