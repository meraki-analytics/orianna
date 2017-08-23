package com.merakianalytics.orianna.datapipeline.riotapi.exceptions;

import com.merakianalytics.orianna.types.common.OriannaException;

public class BadRequestException extends OriannaException {
    private static final long serialVersionUID = 5431308186582990449L;

    public BadRequestException(final String message) {
        super(message);
    }
}
