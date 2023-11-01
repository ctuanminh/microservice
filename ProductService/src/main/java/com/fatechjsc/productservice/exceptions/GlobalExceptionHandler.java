package com.fatechjsc.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception{
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseException handlerException(Exception ex){
        var responseException = new ResponseException();
        responseException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseException.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseException.setMessage(ex.getMessage());
        return  responseException;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private ResponseException handlerException(NotFoundException ex){
        var responseException = new ResponseException();
        responseException.setStatus(HttpStatus.NOT_FOUND);
        responseException.setStatusCode(HttpStatus.NOT_FOUND.value());
        responseException.setMessage(ex.getMessage());
        return  responseException;
    }

    @ExceptionHandler(InvalidParamException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseException handlerException(InvalidParamException ex){
        var responseException = new ResponseException();
        responseException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseException.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseException.setMessage(ex.getMessage());
        return  responseException;
    }

}
