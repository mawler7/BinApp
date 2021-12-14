package com.management.warehouse.exception.container;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContainerNotFoundException extends RuntimeException {
    public ContainerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
