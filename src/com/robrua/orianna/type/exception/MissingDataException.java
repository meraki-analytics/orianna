package com.robrua.orianna.type.exception;

/**
 * Thrown when expected data is missing
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class MissingDataException extends OriannaException {
    private static final long serialVersionUID = -7287920795159022147L;

    /**
     * @param reason
     *            the reason string
     */
    public MissingDataException(final String reason) {
        super(reason);
    }
}
