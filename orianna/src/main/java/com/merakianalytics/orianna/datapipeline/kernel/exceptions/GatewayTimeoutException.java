package com.merakianalytics.orianna.datapipeline.kernel.exceptions;

import com.merakianalytics.orianna.datapipeline.common.TimeoutException.Type;
import com.merakianalytics.orianna.types.common.OriannaException;

public class GatewayTimeoutException extends OriannaException {
    private static final long serialVersionUID = 430582157720690257L;
    private final Type type;

    public GatewayTimeoutException(final String message, final Type type) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
