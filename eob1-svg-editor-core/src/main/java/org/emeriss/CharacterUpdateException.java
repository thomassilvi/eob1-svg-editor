package org.emeriss;

@SuppressWarnings("serial")
public class CharacterUpdateException extends Exception {

    public CharacterUpdateException() {
        super();
    }

    public CharacterUpdateException(String message) {
        super(message);
    }

    public CharacterUpdateException(Throwable cause) {
        super(cause);
    }

    public CharacterUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterUpdateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

