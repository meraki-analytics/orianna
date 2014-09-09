package com.robrua.orianna.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.robrua.easyjava.net.rest.MultiRateLimiter;
import com.robrua.easyjava.net.rest.SingleRateLimiter;
import com.robrua.easyjava.type.Pair;

/**
 * Wrapper for EasyJava's RateLimiter to hide EasyJava from consumers. Used to
 * throttle API requests.
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class RateLimiter implements com.robrua.easyjava.net.rest.RateLimiter {
    /**
     * A rate limit as prescribed by the LoL API
     */
    public static class RateLimit {
        public final int callsPerEpoch;
        public final long millisPerEpoch;

        /**
         * @param callsPerEpoch
         *            the number of allowed called
         * @param millisPerEpoch
         *            the window of time they are allowed in
         */
        public RateLimit(final int callsPerEpoch, final long millisPerEpoch) {
            this.callsPerEpoch = callsPerEpoch;
            this.millisPerEpoch = millisPerEpoch;
        }
    }

    /**
     * @return the default rate limiter for development with the LoL API
     */
    public static RateLimiter defaultDevelopmentRateLimiter() {
        final List<RateLimit> limits = new LinkedList<RateLimit>();
        limits.add(new RateLimit(10, 10000L));
        limits.add(new RateLimit(500, 600000L));
        return new RateLimiter(limits);
    }

    /**
     * @return the default rate limiter for development with the LoL API, but
     *         ignoring the 500/10min restriction
     */
    public static RateLimiter tenPerTenSeconds() {
        return new RateLimiter(10, 10000L);
    }

    private final com.robrua.easyjava.net.rest.RateLimiter rateLimiter;

    /**
     * Use this constructor to manage a single limit
     *
     * @param callsPerEpoch
     *            the number of allowed called
     * @param millisPerEpoch
     *            the window of time they are allowed in
     * @see <a href="http://developer.riotgames.com/">Your Rate Limit</a>
     */
    public RateLimiter(final int callsPerEpoch, final long millisPerEpoch) {
        rateLimiter = new SingleRateLimiter(Type.WINDOW, callsPerEpoch, millisPerEpoch);
    }

    /**
     * Use this constructor to manage multiple limits
     *
     * @param limits
     *            your assigned rate limits
     * @see <a href="http://developer.riotgames.com/">Your Rate Limit</a>
     */
    public RateLimiter(final List<RateLimit> limits) {
        final List<Pair<Integer, Long>> lmts = limits.stream().map((limit) -> new Pair<Integer, Long>(limit.callsPerEpoch, limit.millisPerEpoch))
                .collect(Collectors.toList());
        rateLimiter = new MultiRateLimiter(Type.WINDOW, lmts);
    }

    @Override
    public <T> T attemptCall(final Supplier<T> call) {
        return rateLimiter.attemptCall(call);
    }

    @Override
    public long millisUntilNextCall() {
        return rateLimiter.millisUntilNextCall();
    }

    @Override
    public int numCallsLeft() {
        return rateLimiter.numCallsLeft();
    }

    @Override
    public <T> T waitForCall(final Supplier<T> call) throws InterruptedException {
        return rateLimiter.waitForCall(call);
    }
}
