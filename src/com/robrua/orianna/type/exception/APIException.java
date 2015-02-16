package com.robrua.orianna.type.exception;

public class APIException extends OriannaException {
    private static final long serialVersionUID = 9149283735403841289L;

    public APIException(final String reason) {
        super(reason);
    }
}
