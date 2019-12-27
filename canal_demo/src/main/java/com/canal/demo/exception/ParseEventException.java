package com.canal.demo.exception;

public class ParseEventException extends Exception {

    public ParseEventException() {
        super();
    }

    public ParseEventException(String message) {
        super(message);
    }

    public ParseEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseEventException(Throwable cause) {
        super(cause);
    }

    protected ParseEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
