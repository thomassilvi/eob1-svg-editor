package org.emeriss;

@SuppressWarnings("serial")
public class SaveGameException extends Exception {

    public SaveGameException() {
        super();
    }

    public SaveGameException(String message) {
        super(message);
    }

    public SaveGameException(Throwable cause) {
        super(cause);
    }

    public SaveGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

