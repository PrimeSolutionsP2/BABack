package com.collectionpoints.model.exception;

import lombok.Getter;

@Getter
public enum HttpStatusCode {
    BAD_REQUEST(400),
    NOT_FOUND(404);

    private final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }
}