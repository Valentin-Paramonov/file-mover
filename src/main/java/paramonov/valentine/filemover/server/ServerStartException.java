package paramonov.valentine.filemover.server;

/**
 * Used for signaling that something went wrong during the server startup
 */
public class ServerStartException extends RuntimeException {
    ServerStartException(Exception e) {
        super(e);
    }
}
