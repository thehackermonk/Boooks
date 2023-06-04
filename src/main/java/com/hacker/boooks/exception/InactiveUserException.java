package com.hacker.boooks.exception;

public class InactiveUserException extends RuntimeException {

    public InactiveUserException(String message) {
        super(message);
    }

    public InactiveUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
