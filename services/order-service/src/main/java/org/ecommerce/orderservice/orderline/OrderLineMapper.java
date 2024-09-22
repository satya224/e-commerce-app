package org.ecommerce.orderservice.orderline;

import org.ecommerce.orderservice.order.Order;

import java.util.List;

public class OrderLineMapper {

    private OrderLineMapper() {
    }

    public static OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .order(Order.builder().id(request.orderId()).build())
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }

    public static List<OrderLine> toOrderLines(List<OrderLineRequest> request) {
        return request.stream().map(OrderLineMapper::toOrderLine).toList();
    }

    public static OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
