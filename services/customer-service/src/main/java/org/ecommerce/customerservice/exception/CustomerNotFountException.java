package org.ecommerce.customerservice.exception;

public class CustomerNotFountException extends RuntimeException {
    public CustomerNotFountException(String message) {
        super(message);
    }
}