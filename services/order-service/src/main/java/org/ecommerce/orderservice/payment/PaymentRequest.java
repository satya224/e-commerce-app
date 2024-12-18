package org.ecommerce.orderservice.payment;

import org.ecommerce.commonlib.customer.Customer;
import org.ecommerce.commonlib.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
