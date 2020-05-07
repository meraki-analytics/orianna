package com.merakianalytics.orianna.datapipeline.riotapi.exceptions;

import com.merakianalytics.orianna.types.common.OriannaException;

public class GatewayTimeoutException extends OriannaException {
    private static final long serialVersionUID = 7314447374663868361L;

    public GatewayTimeoutException(final String message) {
        super(message);
    }
}