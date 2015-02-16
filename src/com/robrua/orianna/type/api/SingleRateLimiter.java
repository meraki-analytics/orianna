package com.robrua.orianna.type.api;

import com.robrua.orianna.type.exception.OriannaException;

/**
 * Handles one rate limit
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class SingleRateLimiter implements RateLimiter {
    private long callsLeft;
    private long check;
    private final long limit;
    private final long millisPerEpoch;

    /**
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public SingleRateLimiter(final int callsPerEpoch, final int secondsPerEpoch) {
        millisPerEpoch = secondsPerEpoch * 1000L;
        limit = callsPerEpoch;
        callsLeft = limit;
        check = System.currentTimeMillis();
    }

    /**
     * @param limit
     *            the rate limit
     */
    public SingleRateLimiter(final RateLimit limit) {
        this(limit.callsPerEpoch, limit.secondsPerEpoch);
    }

    @Override
    public synchronized int getCallsLeft() {
        updateCallsLeft();
        return (int)callsLeft;
    }

    @Override
    public synchronized long millisUntilNextCall() {
        updateCallsLeft();
        if(callsLeft >= 1L) {
            return 0L;
        }

        final long millisPassed = System.currentTimeMillis() - check;
        return millisPerEpoch - millisPassed;
    }

    @Override
    public synchronized void registerCall() {
        if(callsLeft == limit) {
            check = System.currentTimeMillis();
        }

        callsLeft--;
    }

    /**
     * Updates the number of remaining calls
     */
    private synchronized void updateCallsLeft() {
        final long millisPassed = System.currentTimeMillis() - check;

        if(millisPassed >= millisPerEpoch) {
            callsLeft = limit;
            check = System.currentTimeMillis();
        }
    }

    @Override
    public synchronized void waitForCall() {
        final long waitTime = millisUntilNextCall();
        if(waitTime > 0) {
            try {
                Thread.sleep(waitTime);
                callsLeft = limit;
            }
            catch(final InterruptedException e) {
                throw new OriannaException("Rate limiter was interrupted waiting for call!");
            }
        }
    }
}
