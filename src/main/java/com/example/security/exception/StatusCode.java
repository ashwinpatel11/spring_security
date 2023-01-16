package com.example.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public enum StatusCode {


    WRONG_PASSWORD(601, HttpStatus.Series.INFORMATIONAL,"password not match"),
    VALUE_NOT_FOUND(602, HttpStatus.Series.INFORMATIONAL, "value not found"),
    BAD_REQUEST(603, HttpStatus.Series.INFORMATIONAL, "Bad request"),
    OK(605, HttpStatus.Series.SUCCESSFUL, "OK");

    //private static final HttpStatus[] VALUES = values();
    private final int value;
    private final HttpStatus.Series series;
    private final String reasonPhrase;

    private StatusCode(int value, HttpStatus.Series series, String reasonPhrase) {
        this.value = value;
        this.series = series;
        this.reasonPhrase = reasonPhrase;
    }

}
