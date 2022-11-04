package com.nixalevel.exceptions;

public class LSException extends Exception {
    public LSException() {
        super();
    }
    public LSException(String message) {
        super(message);
    }
    public LSException(String message, Throwable cause) {
        super(message, cause);
    }
    public LSException(Throwable cause) {
        super(cause);
    }
}
