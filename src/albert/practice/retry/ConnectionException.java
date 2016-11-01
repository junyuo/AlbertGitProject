package albert.practice.retry;

public class ConnectionException extends Exception {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
