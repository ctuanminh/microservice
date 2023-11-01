package com.fatechjsc.identityservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e){
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    }

    @ExceptionHandler(PermissionDenyException.class)
    public ResponseEntity<ErrorResponse> handleDeny(PermissionDenyException ex){
        var errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse NotFoundExceptionHandle(NotFoundException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }
}
