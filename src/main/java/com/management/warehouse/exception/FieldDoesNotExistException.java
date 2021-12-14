package com.management.warehouse.exception;

public class FieldDoesNotExistException extends RuntimeException {
    public FieldDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
