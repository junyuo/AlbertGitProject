package albert.practice.collection;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ComConfigException extends Exception {

    public ComConfigException(String message) {
        super(message);
    }

    public ComConfigException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ComConfigException(Throwable throwable) {
        super(throwable);
    }

}
