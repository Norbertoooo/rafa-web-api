package com.rafa.web.api.web.exceptionHandler;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
