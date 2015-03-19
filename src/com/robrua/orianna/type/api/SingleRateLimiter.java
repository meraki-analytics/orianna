package com.robrua.orianna.type.api;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * Handles one rate limit
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class SingleRateLimiter implements RateLimiter {
    /**
     * Resets the number of available permits
     */
    private class ResetTask extends TimerTask {
        @Override
        public void run() {
            semaphore.drainPermits();
            resetter = null;
            semaphore.release(limit - current);
        }
    }

    private volatile int current;
    private final int limit;
    private final long millisPerEpoch;
    private volatile ResetTask resetter;
    private final Semaphore semaphore;
    private final Timer timer;

    /**
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public SingleRateLimiter(final int callsPerEpoch, final int secondsPerEpoch) {
        millisPerEpoch = secondsPerEpoch * 1000L;
        limit = callsPerEpoch;
        semaphore = new Semaphore(limit, true);
        timer = new Timer(true);
        current = 0;
    }

    /**
     * @param limit
     *            the rate limit
     */
    public SingleRateLimiter(final RateLimit limit) {
        this(limit.callsPerEpoch, limit.secondsPerEpoch);
    }

    @Override
    public synchronized void registerCall() {
        if(resetter == null) {
            resetter = new ResetTask();
            timer.schedule(resetter, millisPerEpoch);
        }

        current--;
    }

    @Override
    public synchronized void resetIn(final long millis) {
        if(resetter != null) {
            resetter.cancel();
        }
        resetter = new ResetTask();
        semaphore.drainPermits();
        timer.schedule(resetter, millis);
    }

    @Override
    public void waitForCall() {
        semaphore.acquireUninterruptibly();
        current++;
    }
}
