package com.merakianalytics.orianna.datapipeline.riotapi.exceptions;

import com.merakianalytics.orianna.types.common.OriannaException;

public class UnauthorizedException extends OriannaException {
    private static final long serialVersionUID = -7024907949361933670L;

    public UnauthorizedException(final String message) {
        super(message);
    }
}
