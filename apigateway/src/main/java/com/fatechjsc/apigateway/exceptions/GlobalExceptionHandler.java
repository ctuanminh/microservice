package com.fatechjsc.apigateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {

    @ExceptionHandler(InternalServerException.class)
    public ResponseException handler(InternalServerException ex){
        var response = new ResponseException();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setMessage(ex.getLocalizedMessage());
        return response;
    }
}
