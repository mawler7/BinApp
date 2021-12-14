package com.management.warehouse.exception.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUserOrPasswordException extends RuntimeException {
    public InvalidUserOrPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
