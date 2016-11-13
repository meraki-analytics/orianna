package com.robrua.orianna.type.api;

import com.robrua.orianna.type.core.common.Region;

/**
 * Used to limit calls to the API on the client side to avoid overloading Riot's
 * servers
 *
 * @author Rob Rua (robrua@alumni.cmu.edu)
 */
public interface RateLimiter {
    /**
     * Registers that a call has been made. Call this after making a call to
     * ensure synchronization with the server
     * @param region
     */
    void registerCall(Region region);

    /**
     * Cancels current operation and resets the rate limiter
     *
     * @param region
     * @param millis
     */
    void resetIn(Region region, long millis);

    /**
     * Blocks until a call is available
     * @param region
     */
    void waitForCall(Region region);
}
