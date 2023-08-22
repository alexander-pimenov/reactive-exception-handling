package com.javatechie.reactive.exception;

public class ThreadInterruptedException extends RuntimeException {
    public ThreadInterruptedException(Exception ex) {
        super(ex);
    }
}
