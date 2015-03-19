package com.robrua.orianna.type.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles multiple rate limits
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class MultiRateLimiter implements RateLimiter {
    private final Set<SingleRateLimiter> limits;

    /**
     * @param limits
     *            the rate limits to use
     */
    public MultiRateLimiter(final Collection<RateLimit> limits) {
        this.limits = new HashSet<>();
        for(final RateLimit limit : limits) {
            this.limits.add(new SingleRateLimiter(limit));
        }
    }

    /**
     * @param limits
     *            the rate limits to use
     */
    public MultiRateLimiter(final RateLimit... limits) {
        this(Arrays.asList(limits));
    }

    @Override
    public synchronized void registerCall() {
        for(final RateLimiter limit : limits) {
            limit.registerCall();
        }
    }

    @Override
    public synchronized void resetIn(final long millis) {
        for(final RateLimiter limit : limits) {
            limit.resetIn(millis);
        }
    }

    @Override
    public synchronized void waitForCall() {
        for(final RateLimiter limit : limits) {
            limit.waitForCall();
        }
    }
}
