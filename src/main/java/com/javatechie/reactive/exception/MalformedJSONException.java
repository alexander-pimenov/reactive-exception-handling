package com.javatechie.reactive.exception;

public class MalformedJSONException extends RuntimeException {
    public MalformedJSONException(Exception ex) {
        super(ex);
    }

    public MalformedJSONException(String message) {
        super(message);
    }
}
