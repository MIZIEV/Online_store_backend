package com.storeApp.util.exception;

import org.springframework.http.HttpStatus;

public final class IncorrectUsernameOrPasswordException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

    public IncorrectUsernameOrPasswordException(HttpStatus httpStatus, String message){
        this.httpStatus=httpStatus;
        this.message=message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}