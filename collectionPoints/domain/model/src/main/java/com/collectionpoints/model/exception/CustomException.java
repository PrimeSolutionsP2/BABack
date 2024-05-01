package com.collectionpoints.model.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception{
    private final int statusCode;
    private final String message;

    public CustomException(int statusCode, String message) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
