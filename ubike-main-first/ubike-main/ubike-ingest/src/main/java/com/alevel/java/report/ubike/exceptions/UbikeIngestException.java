package com.alevel.java.report.ubike.exceptions;

public class UbikeIngestException extends Exception {

    public UbikeIngestException() {
    }

    public UbikeIngestException(String message) {
        super(message);
    }

    public UbikeIngestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UbikeIngestException(Throwable cause) {
        super(cause);
    }
}
