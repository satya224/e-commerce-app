package org.ecommerce.orderservice.payment;

import org.ecommerce.orderservice.customer.CustomerResponse;
import org.ecommerce.orderservice.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
