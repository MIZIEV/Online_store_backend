package com.storeApp.util.exception;

import org.springframework.http.HttpStatus;

public class OnlineStoreApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public OnlineStoreApiException(HttpStatus httpStatus,String message){
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