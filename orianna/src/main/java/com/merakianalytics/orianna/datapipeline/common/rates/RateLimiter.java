package com.merakianalytics.orianna.datapipeline.common.rates;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface RateLimiter {
    public static interface ReservedPermit {
        public void acquire();

        public void cancel();
    }

    public static enum Type {
        BURST(FixedWindowRateLimiter.class);

        private final Class<? extends AbstractRateLimiter> clazz;

        private Type(final Class<? extends AbstractRateLimiter> clazz) {
            this.clazz = clazz;
        }

        public Class<? extends AbstractRateLimiter> getLimiterClass() {
            return clazz;
        }
    }

    public void acquire() throws InterruptedException;

    public boolean acquire(final long timeout, final TimeUnit unit) throws InterruptedException;

    public <T> T call(final Callable<T> callable) throws InterruptedException, Exception;

    public <T> T call(final Callable<T> callable, final long timeout, final TimeUnit unit) throws InterruptedException, Exception;

    public void call(final Runnable runnable) throws InterruptedException;

    public void call(final Runnable runnable, final long timeout, final TimeUnit unit) throws InterruptedException;

    public int permitsIssued();

    public void release();

    public ReservedPermit reserve() throws InterruptedException;

    public ReservedPermit reserve(final long timeout, final TimeUnit unit) throws InterruptedException;

    public void restrict(long afterTime, TimeUnit afterUnit, long forTime, TimeUnit forUnit);

    public void restrictFor(final long time, final TimeUnit unit);
}
