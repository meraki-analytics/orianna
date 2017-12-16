package com.merakianalytics.orianna.datapipeline.common;

public class QueryValidationException extends IllegalArgumentException {
    private static final long serialVersionUID = 5500321793855768637L;

    public QueryValidationException(final String message) {
        super(message);
    }
}
