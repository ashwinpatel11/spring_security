package com.example.webosmotic.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomException {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> exceptionHandle() {
        System.out.println("exception occurs");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity tokenExpire() {
        System.out.println("token expire");
        return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredential() {
        System.out.println("Bad credential");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


