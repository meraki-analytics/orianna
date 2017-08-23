package com.merakianalytics.orianna.datapipeline.riotapi.exceptions;

import com.merakianalytics.orianna.types.common.OriannaException;

public class NotFoundException extends OriannaException {
    private static final long serialVersionUID = -349203811791575505L;

    public NotFoundException(final String message) {
        super(message);
    }
}
