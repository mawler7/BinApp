package com.management.warehouse.exception;

public class ContainerAmountException extends RuntimeException {
    public ContainerAmountException(String errorMessage) {
        super(errorMessage);
    }
}
