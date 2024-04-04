package com.collectionpoint.api.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class GenericResponse<T> {
    public GenericResponse(int statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }
    private int statusCode;
    private T data;
    private String message;
}
