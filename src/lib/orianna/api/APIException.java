package lib.orianna.api;

/**
 * Encapsulates all common error conditions from the API
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class APIException extends RuntimeException {
    public static enum Type {
        BAD_REQUEST, DATA_NOT_FOUND, PARSE_FAILURE, RATE_LIMITED, SERVER_ERROR, UNAUTHORIZED, UNAVAILABLE, UNKNOWN;
    }

    private static final long serialVersionUID = 5015141496105277138L;

    private static String getMessage(final Type type) {
        switch(type) {
            case BAD_REQUEST:
                return "Bad request";
            case DATA_NOT_FOUND:
                return "Requested data not found";
            case PARSE_FAILURE:
                return "Failed to parse Riot's JSON response";
            case RATE_LIMITED:
                return "Rate limit exceeded";
            case SERVER_ERROR:
                return "Internal server error";
            case UNAUTHORIZED:
                return "Unauthorized";
            case UNAVAILABLE:
                return "Service unavailable";
            case UNKNOWN:
                return "An unknown API error occured";
            default:
                return "An unknown API error occured";
        }
    }

    public final Exception cause;
    public final Type type;

    /**
     * @param type
     *            the type of error that occurred
     * @param cause
     *            the Exception that was thrown
     */
    public APIException(final Type type, final Exception cause) {
        super(getMessage(type));
        this.type = type;
        this.cause = cause;
    }

    /**
     * @param type
     *            the type of error that occurred
     * @param URL
     *            the URL that was accessed when the Exception was thrown
     * @param cause
     *            the Exception that was thrown
     */
    public APIException(final Type type, final String URL, final Exception cause) {
        super(getMessage(type) + " for request: " + URL);
        this.type = type;
        this.cause = cause;
    }
}
