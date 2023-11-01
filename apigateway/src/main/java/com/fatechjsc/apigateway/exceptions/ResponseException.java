package com.fatechjsc.apigateway.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseException extends RuntimeException {
    private HttpStatus status;
    private String message;
    public ResponseException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
