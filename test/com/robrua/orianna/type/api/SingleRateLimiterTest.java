package com.robrua.orianna.type.api;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Dominic Gunn (Dominic.Gunn@protonmail.ch)
 */
public class SingleRateLimiterTest {

    private static final int TOTAL_CALLS = 5;
    private static final int TIME_LIMIT_IN_SECONDS = 3;

    private SingleRateLimiter singleRateLimiter;

    @Before
    public void setUp() throws Exception {
        singleRateLimiter = new SingleRateLimiter(TOTAL_CALLS, TIME_LIMIT_IN_SECONDS);
    }

    @Test
    public void testUsageWithinLimit() throws Exception {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < TOTAL_CALLS; i++) {
            singleRateLimiter.waitForCall(null);
            singleRateLimiter.registerCall(null);
        }
        assertTrue(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - starTime) < TIME_LIMIT_IN_SECONDS);
    }

    @Test
    public void testUsageExceedingLimit() throws Exception {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < (TOTAL_CALLS + 1); i++) {
            singleRateLimiter.waitForCall(null);
            singleRateLimiter.registerCall(null);
        }
        assertFalse(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - starTime) < TIME_LIMIT_IN_SECONDS);
    }
}
