package com.example.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg){
        super(msg);

    }
}
