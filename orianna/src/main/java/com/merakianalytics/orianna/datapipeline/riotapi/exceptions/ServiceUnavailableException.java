package com.merakianalytics.orianna.datapipeline.riotapi.exceptions;

import com.merakianalytics.orianna.types.common.OriannaException;

public class ServiceUnavailableException extends OriannaException {
    private static final long serialVersionUID = 5431308186582990449L;

    public ServiceUnavailableException(final String message) {
        super(message);
    }
}
