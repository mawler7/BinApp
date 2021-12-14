package com.management.warehouse.exception.container;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContainerAlreadyExistException extends RuntimeException {
    public ContainerAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
