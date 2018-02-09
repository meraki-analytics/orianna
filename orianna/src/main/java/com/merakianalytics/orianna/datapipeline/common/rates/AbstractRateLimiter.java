package com.merakianalytics.orianna.datapipeline.common.rates;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.merakianalytics.orianna.datapipeline.common.TimeoutException;

public abstract class AbstractRateLimiter implements RateLimiter {
    public AbstractRateLimiter(final int permits, final long epoch, final TimeUnit epochUnit) {}

    @Override
    public abstract void acquire() throws InterruptedException;

    @Override
    public abstract boolean acquire(final long timeout, final TimeUnit unit) throws InterruptedException;

    @Override
    public <T> T call(final Callable<T> callable) throws InterruptedException, Exception {
        acquire();

        try {
            return callable.call();
        } finally {
            release();
        }
    }

    @Override
    public <T> T call(final Callable<T> callable, final long timeout, final TimeUnit unit) throws InterruptedException, Exception {
        if(!acquire(timeout, unit)) {
            throw new TimeoutException("Rate Limiter timed out waiting for permit!", TimeoutException.Type.RATE_LIMITER);
        }

        try {
            return callable.call();
        } finally {
            release();
        }
    }

    @Override
    public void call(final Runnable runnable) throws InterruptedException {
        acquire();

        try {
            runnable.run();
        } finally {
            release();
        }
    }

    @Override
    public void call(final Runnable runnable, final long timeout, final TimeUnit unit) throws InterruptedException {
        if(!acquire(timeout, unit)) {
            throw new TimeoutException("Rate Limiter timed out waiting for permit!", TimeoutException.Type.RATE_LIMITER);
        }

        try {
            runnable.run();
        } finally {
            release();
        }
    }

    public abstract long getEpoch();

    public abstract TimeUnit getEpochUnit();

    public abstract int getPermits();

    @Override
    public abstract void release();

    @Override
    public abstract void restrictFor(final long time, final TimeUnit unit);

    public abstract void setPermits(final int permits);
}
