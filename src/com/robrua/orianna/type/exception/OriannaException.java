package com.robrua.orianna.type.exception;

/**
 * Thrown for any Orianna error
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class OriannaException extends RuntimeException {
    private static final long serialVersionUID = -2917612254587744044L;

    /**
     * @param reason
     *            the reason string
     */
    public OriannaException(final String reason) {
        super(reason);
    }
}
