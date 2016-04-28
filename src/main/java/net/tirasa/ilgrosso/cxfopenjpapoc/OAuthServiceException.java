package net.tirasa.ilgrosso.cxfopenjpapoc;

/**
 * Encapsulates OAuth-related problems
 */
public class OAuthServiceException extends RuntimeException {

    private static final long serialVersionUID = 343738539234766320L;

    public OAuthServiceException() {
        super(OAuthConstants.SERVER_ERROR);
    }

    public OAuthServiceException(String message) {
        super(message);
    }

    public OAuthServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuthServiceException(Throwable cause) {
        super(OAuthConstants.SERVER_ERROR, cause);
    }
}
