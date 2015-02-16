package com.robrua.orianna.type.api;

/**
 * Used to limit the rate fo calls to the API client-side
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class RateLimit {
    public final int callsPerEpoch, secondsPerEpoch;

    /**
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public RateLimit(final int callsPerEpoch, final int secondsPerEpoch) {
        this.callsPerEpoch = callsPerEpoch;
        this.secondsPerEpoch = secondsPerEpoch;
    }
}
