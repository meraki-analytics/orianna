package com.merakianalytics.orianna.datapipeline.riotapi.exceptions;

import com.merakianalytics.orianna.type.common.OriannaException;

public class InternalServerErrorException extends OriannaException {
    private static final long serialVersionUID = 5431308186582990449L;

    public InternalServerErrorException(final String message) {
        super(message);
    }
}
