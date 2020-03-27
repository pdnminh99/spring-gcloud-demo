package com.example.bookmanagement.CustomExceptions;

public class BlobNotFoundException extends Exception {
    public BlobNotFoundException() {
        super();
    }

    public BlobNotFoundException(String message) {
        super(message);
    }

    public BlobNotFoundException(Throwable cause) {
        super(cause);
    }
}
