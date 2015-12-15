package com.robrua.orianna.type.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Thrown when the Riot API gives an error
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class APIException extends OriannaException {
    public static enum Status {
        BAD_REQUEST(400), INTERNAL_SERVER_ERROR(500), NOT_FOUND(404), RATE_LIMIT_EXCEEDED(429), SERVICE_UNAVAILABLE(503), UNAUTHORIZED(401), UNKNOWN(0);

        private static final Map<Integer, Status> codes = new HashMap<>();

        static {
            for(final Status m : Status.values()) {
                codes.put(m.code, m);
            }
        }

        public static Status forCode(final int code) {
            final Status t = codes.get(code);
            if(t == null) {
                return UNKNOWN;
            }
            return t;
        }

        private final int code;

        private Status(final int code) {
            this.code = code;
        }
    }

    private static final long serialVersionUID = 9149283735403841289L;
    private final Status status;

    /**
     * @param URI
     *            the URI that was being accessed when this error occured
     * @param statusCode
     *            the HTTP status code returned by the Riot API
     */
    public APIException(final String URI, final int statusCode) {
        super("A " + Status.forCode(statusCode) + " (" + statusCode + ") error was received from the server for URI " + URI);
        status = Status.forCode(statusCode);
    }

    /**
     * @return the HTTP status returned by the Riot API
     */
    public Status getStatus() {
        return status;
    }
}
