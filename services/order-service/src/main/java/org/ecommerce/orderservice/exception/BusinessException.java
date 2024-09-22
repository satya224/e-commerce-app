package org.ecommerce.orderservice.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
