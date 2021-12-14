package com.management.warehouse.exception.truck;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TruckAlreadyExistException extends RuntimeException {
    public TruckAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
