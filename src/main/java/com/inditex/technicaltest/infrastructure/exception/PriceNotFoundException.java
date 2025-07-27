package com.inditex.technicaltest.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PriceNotFoundException extends RuntimeException {

    private final HttpStatus status;

    /**
     * Constructs a new PriceNotFoundException with the specified detail message and HTTP status.
     *
     * @param msg    the detail message
     * @param status the HTTP status to be returned
     */
    public PriceNotFoundException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

}