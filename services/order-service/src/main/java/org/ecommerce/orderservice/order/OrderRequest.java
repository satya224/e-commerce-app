package org.ecommerce.orderservice.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ecommerce.orderservice.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotBlank(message = "Customer id is required")
        String customerId,
        @NotEmpty(message = "Order should have at least one product")
        List<PurchaseRequest> products
) {}
