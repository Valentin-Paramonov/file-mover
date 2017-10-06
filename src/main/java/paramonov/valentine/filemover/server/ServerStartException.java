package paramonov.valentine.filemover.server;

public class ServerStartException extends RuntimeException {
    ServerStartException(Exception e) {
        super(e);
    }
}
