package com.management.warehouse.exception.truck;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TruckNotFoundException extends RuntimeException {
    public TruckNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
