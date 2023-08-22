package com.javatechie.reactive.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


public class BookAPIException extends Exception {
    @Getter
    @Setter
    private HttpStatus httpStatus;

    public BookAPIException(String message) {
        super(message);
    }

    public BookAPIException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
