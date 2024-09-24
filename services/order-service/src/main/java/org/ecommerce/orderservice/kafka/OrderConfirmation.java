package org.ecommerce.orderservice.kafka;

import org.ecommerce.orderservice.customer.CustomerResponse;
import org.ecommerce.orderservice.order.PaymentMethod;
import org.ecommerce.orderservice.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
