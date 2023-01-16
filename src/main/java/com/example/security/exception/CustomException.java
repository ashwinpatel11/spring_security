package com.example.security.exception;

import com.example.security.model.ExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CustomException {
     @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> exceptionHandle(NullPointerException ne) {
        System.out.println("exception occurs "+ne.getMessage());
        return new ResponseEntity<>(ne.getMessage(),HttpStatus.GATEWAY_TIMEOUT);

    }

    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity tokenExpire() {
        System.out.println("token expire");
        return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ExceptionResponse badCredential(BusinessException be) {
         ExceptionResponse er=new ExceptionResponse(new Date(),StatusCode.VALUE_NOT_FOUND.toString(),be.getMessage());
/*
        Map<String,String> map=new HashMap<>();
        map.put("errorMsg", be.getMessage());
        map.put("errorCode",HttpStatus.BAD_REQUEST.toString());

        System.out.println("Bad credential  "+map.toString());*/
        return er;

    }
}
