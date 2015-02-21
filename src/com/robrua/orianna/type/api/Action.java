package com.robrua.orianna.type.api;

/**
 * Used for the AsyncAPI to ease asynchronous API calls
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class Action<T> {
    /**
     * Performs the action on the API response data
     *
     * @param responseData
     *            the API call's result
     */
    public abstract void perform(T responseData);
}
