package com.robrua.orianna.type.api;

/**
 * Used to limit calls to the API on the client side to avoid overloading Riot's
 * servers
 *
 * @author Rob Rua (robrua@alumni.cmu.edu)
 */
public interface RateLimiter {
    /**
     * Gets the number of calls currently available
     *
     * @return the number of calls currently available
     */
    public int getCallsLeft();

    /**
     * Gets the number of milliseconds until another call is available
     *
     * @return the number of milliseconds until another call is available
     */
    public long millisUntilNextCall();

    /**
     * Registers that a call has been made. Call this after making a call to
     * ensure synchronization with the server
     */
    public void registerCall();

    /**
     * Blocks until a call is available
     */
    public void waitForCall();
}
