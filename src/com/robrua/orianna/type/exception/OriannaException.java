package com.robrua.orianna.type.exception;

public class OriannaException extends RuntimeException {
    private static final long serialVersionUID = -2917612254587744044L;

    public OriannaException(final String reason) {
        super(reason);
    }
}
