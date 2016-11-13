package com.robrua.orianna.type.api;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Dominic Gunn (Dominic.Gunn@protonmail.ch)
 */
public class MultiRateLimiterTest {

    private static final int FIRST_POLICY_TOTAL_CALLS = 2;
    private static final int FIRST_POLICY_TIME_LIMIT_IN_SECONDS = 3;

    private static final int SECOND_POLICY_TOTAL_CALLS = 4;
    private static final int SECOND_POLICY_TIME_LIMIT_IN_SECONDS = 4;

    private MultiRateLimiter multiRateLimiter;

    @Before
    public void setUp() throws Exception {
        multiRateLimiter = new MultiRateLimiter(
                new RateLimit(FIRST_POLICY_TOTAL_CALLS, FIRST_POLICY_TIME_LIMIT_IN_SECONDS),
                new RateLimit(SECOND_POLICY_TOTAL_CALLS, SECOND_POLICY_TIME_LIMIT_IN_SECONDS)
        );
    }

    @Test
    public void testFullPolicyWithinFirstLimit() throws Exception {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < FIRST_POLICY_TOTAL_CALLS; i++) {
            multiRateLimiter.waitForCall(null);
            multiRateLimiter.registerCall(null);
        }
        assertTrue(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - starTime) < FIRST_POLICY_TIME_LIMIT_IN_SECONDS);
    }

    @Test
    public void testFullPolicyWithinSecondLimit() throws Exception {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < SECOND_POLICY_TOTAL_CALLS; i++) {
            multiRateLimiter.waitForCall(null);
            multiRateLimiter.registerCall(null);
        }
        assertTrue(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - starTime) < SECOND_POLICY_TIME_LIMIT_IN_SECONDS);
    }

    @Test
    public void testFullPolicyExceedsLimit() throws Exception {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < SECOND_POLICY_TOTAL_CALLS; i++) {
            multiRateLimiter.waitForCall(null);
            multiRateLimiter.registerCall(null);
        }
        assertFalse(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - starTime) < FIRST_POLICY_TOTAL_CALLS);
    }
}
