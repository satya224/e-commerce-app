package org.ecommerce.orderservice.orderline;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        Double quantity
) {}
