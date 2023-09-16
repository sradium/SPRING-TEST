package com.catedra.test.domain.exception;


import org.springframework.http.HttpStatus;

import lombok.Getter;


@Getter
public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public HttpException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}