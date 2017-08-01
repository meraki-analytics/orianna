package com.merakianalytics.orianna.datapipeline.common.rates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.merakianalytics.orianna.datapipeline.common.TimeoutException;

public class MultiRateLimiter implements RateLimiter {
    private final Map<String, RateLimiter> limiters;
    private final AtomicInteger permitsIssued = new AtomicInteger(0);

    public MultiRateLimiter(final Collection<? extends RateLimiter> limiters) {
        final Map<String, RateLimiter> map = new HashMap<>();
        int i = 0;
        for(final RateLimiter limiter : limiters) {
            map.put(Integer.toString(i++), limiter);
        }
        this.limiters = Collections.unmodifiableMap(map);
    }

    public MultiRateLimiter(final Map<String, ? extends RateLimiter> limiters) {
        this.limiters = Collections.unmodifiableMap(new HashMap<>(limiters));
    }

    public MultiRateLimiter(final RateLimiter... limiters) {
        this(Arrays.asList(limiters));
    }

    @Override
    public void acquire() throws InterruptedException {
        for(final RateLimiter limiter : limiters.values()) {
            limiter.acquire();
        }
    }

    @Override
    public boolean acquire(final long timeout, final TimeUnit unit) throws InterruptedException {
        final long deadline = System.currentTimeMillis() + unit.toMillis(timeout);

        final List<RateLimiter> acquired = new ArrayList<>(limiters.size());
        for(final RateLimiter limiter : limiters.values()) {
            final long left = deadline - System.currentTimeMillis();
            if(limiter.acquire(left, TimeUnit.MILLISECONDS)) {
                acquired.add(limiter);
            } else {
                for(final RateLimiter acquiredLimiter : acquired) {
                    acquiredLimiter.cancel();
                }
                return false;
            }
        }

        return true;
    }

    @Override
    public <T> T call(final Callable<T> callable) throws InterruptedException, Exception {
        acquire();
        permitsIssued.incrementAndGet();
        final T result = callable.call();
        release();
        return result;
    }

    @Override
    public <T> T call(final Callable<T> callable, final long timeout, final TimeUnit unit) throws InterruptedException, Exception {
        if(!acquire(timeout, unit)) {
            throw new TimeoutException("Rate Limiter timed out waiting for permit!", TimeoutException.Type.RATE_LIMITER);
        }

        permitsIssued.incrementAndGet();
        final T result = callable.call();
        release();
        return result;
    }

    @Override
    public void call(final Runnable runnable) throws InterruptedException {
        acquire();
        permitsIssued.incrementAndGet();
        runnable.run();
        release();
    }

    @Override
    public void call(final Runnable runnable, final long timeout, final TimeUnit unit) throws InterruptedException {
        if(!acquire(timeout, unit)) {
            throw new TimeoutException("Rate Limiter timed out waiting for permit!", TimeoutException.Type.RATE_LIMITER);
        }

        permitsIssued.incrementAndGet();
        runnable.run();
        release();
    }

    @Override
    public void cancel() {
        for(final RateLimiter limiter : limiters.values()) {
            limiter.cancel();
        }
    }

    public RateLimiter limiter(final String name) {
        return limiters.get(name);
    }

    public Collection<RateLimiter> limiters() {
        return limiters.values();
    }

    @Override
    public int permitsIssued() {
        return permitsIssued.get();
    }

    @Override
    public void release() {
        for(final RateLimiter limiter : limiters.values()) {
            limiter.release();
        }
    }

    @Override
    public void restrictFor(final long time, final TimeUnit unit) {
        for(final RateLimiter limiter : limiters.values()) {
            limiter.restrictFor(time, unit);
        }
    }
}
