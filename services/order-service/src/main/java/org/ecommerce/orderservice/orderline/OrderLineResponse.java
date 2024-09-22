package org.ecommerce.orderservice.orderline;

public record OrderLineResponse(
        Integer id,
        Double quantity
) {}
