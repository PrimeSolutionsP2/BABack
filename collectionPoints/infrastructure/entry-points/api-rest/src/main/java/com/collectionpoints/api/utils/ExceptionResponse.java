package com.collectionpoints.api.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private int statusCode;
    private String message;
}
