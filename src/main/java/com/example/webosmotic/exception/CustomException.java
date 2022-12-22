package com.example.webosmotic.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomException {

    /*@ResponseStatus()
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(){
        return "unauthorised access";
    }*/

    @ExceptionHandler(ExpiredJwtException.class)
    public String tokenExpire(){
        System.out.println("token expire");
        return "token is expire";
    }
}


