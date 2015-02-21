package com.robrua.orianna.type.api;

import java.util.concurrent.Semaphore;

import com.robrua.orianna.type.exception.OriannaException;

/**
 * Handles one rate limit
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class SingleRateLimiter implements RateLimiter {
    /**
     * Resets the semaphore after the time window has passed
     */
    private class ResetThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(millisPerEpoch);
                semaphore.drainPermits();
                semaphore.release(limit);
                needToWait = true;
            }
            catch(final InterruptedException e) {
                throw new OriannaException("Rate Limiter reset thread interrupted!");
            }
        }
    }

    private final int limit;
    private final long millisPerEpoch;
    private volatile boolean needToWait;

    private final Semaphore semaphore;

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
        needToWait = true;
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
        if(needToWait) {
            needToWait = false;
            final Thread t = new Thread(new ResetThread());
            t.setDaemon(true);
            t.start();
        }
    }

    @Override
    public void waitForCall() {
        semaphore.acquireUninterruptibly();
    }
}
