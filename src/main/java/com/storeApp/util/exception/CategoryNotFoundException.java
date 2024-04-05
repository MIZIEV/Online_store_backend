package com.storeApp.util.exception;

import org.springframework.http.HttpStatus;

public final class CategoryNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

    public CategoryNotFoundException(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
