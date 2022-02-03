package com.management.warehouse.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
