package com.payfort.sdk.exception;

final public class FortException extends RuntimeException {
    public FortException() {
    }

    public FortException(String message) {
        super(message);
    }

    public FortException(String message, Throwable cause) {
        super(message, cause);
    }

    public FortException(Throwable cause) {
        super(cause);
    }

    public FortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
