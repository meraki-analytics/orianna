package com.robrua.orianna.type.api;

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
     */
    public void registerCall();

    /**
     * Cancels current operation and resets the rate limiter
     *
     * @param millis
     *            the number of milliseconds to wait before resetting
     */
    public void resetIn(long millis);

    /**
     * Blocks until a call is available
     */
    public void waitForCall();
}
