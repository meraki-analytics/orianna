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
            resetRunning = false;
            semaphore.release(limit - current);
        }
    }

    private volatile int current;
    private final int limit;
    private final long millisPerEpoch;
    private volatile boolean resetRunning;
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
        semaphore = new Semaphore(limit);
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
        if(!resetRunning) {
            timer.schedule(new ResetTask(), millisPerEpoch);
            resetRunning = true;
        }

        current--;
    }

    @Override
    public void waitForCall() {
        semaphore.acquireUninterruptibly();
        current++;
    }
}
