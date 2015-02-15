package com.robrua.orianna.type.exception;

public class MissingDataException extends OriannaException {
    private static final long serialVersionUID = -7287920795159022147L;

    public MissingDataException(final String reason) {
        super(reason);
    }
}
