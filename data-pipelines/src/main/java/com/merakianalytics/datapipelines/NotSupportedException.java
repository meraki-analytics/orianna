package com.merakianalytics.datapipelines;

public class NotSupportedException extends RuntimeException {
    private static final long serialVersionUID = -1727811546087506000L;

    public NotSupportedException(final String message) {
        super(message);
    }
}