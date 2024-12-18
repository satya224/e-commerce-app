package org.ecommerce.commonlib.order;

import org.ecommerce.commonlib.customer.Customer;
import org.ecommerce.commonlib.payment.PaymentMethod;
import org.ecommerce.commonlib.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
