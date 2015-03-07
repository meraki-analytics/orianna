package com.robrua.orianna.type.api;

import com.robrua.orianna.type.exception.APIException;

/**
 * Used for the AsyncAPI to ease asynchronous API calls
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class Action<T> {
    /**
     * Handles an APIException which occurred while completing an async call
     *
     * @param exception
     *            the exception to handle
     */
    public abstract void handle(APIException exception);

    /**
     * Performs the action on the API response data
     *
     * @param responseData
     *            the API call's result
     */
    public abstract void perform(T responseData);
}
