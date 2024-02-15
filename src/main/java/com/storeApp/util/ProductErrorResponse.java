package com.storeApp.util;

import java.time.LocalDateTime;

public class ProductErrorResponse {

    private String errorMessage;

    private LocalDateTime timestamp;

    public ProductErrorResponse(String errorMessage, LocalDateTime timestamp) {
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}