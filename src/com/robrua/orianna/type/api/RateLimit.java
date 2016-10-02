package com.robrua.orianna.type.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used to limit the rate fo calls to the API client-side
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class RateLimit {

    public static final List<RateLimit> DEVELOPMENT_RATE_LIMITS = Arrays.asList(
            new RateLimit(10, 10), new RateLimit(500, 600)
    );

    public static final List<RateLimit> PRODUCTION_RATE_LIMITS = Arrays.asList(
            new RateLimit(3000, 10), new RateLimit(180000, 600)
    );

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
