package com.fatechjsc.apigateway.exceptions;

public class InternalServerException extends Exception {
    public InternalServerException(String message){
        super(message);
    }
}
