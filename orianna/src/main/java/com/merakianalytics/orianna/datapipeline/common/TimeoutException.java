package com.merakianalytics.orianna.datapipeline.common;

import com.merakianalytics.orianna.type.common.OriannaException;

public class TimeoutException extends OriannaException {
    public static enum Type {
        HTTP,
        RATE_LIMITER;
    }

    private static final long serialVersionUID = -1889177348125407210L;
    private final Type type;

    public TimeoutException(final String message, final Type type) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}