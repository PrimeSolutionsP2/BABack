package com.collectionpoints.api.utils;

import com.collectionpoints.model.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GenericResponse> handleCustomError(CustomException e) {
        GenericResponse response = new GenericResponse(e.getStatusCode(), null, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleCustomError(Exception e) {
        e.printStackTrace();
        GenericResponse response = new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "INTERNAL SERVER ERROR");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
