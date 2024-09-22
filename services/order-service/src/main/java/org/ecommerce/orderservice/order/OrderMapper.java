package org.ecommerce.orderservice.order;

import jakarta.validation.Valid;
import org.ecommerce.orderservice.customer.CustomerResponse;
import org.ecommerce.orderservice.kafka.OrderConfirmation;
import org.ecommerce.orderservice.payment.PaymentRequest;
import org.ecommerce.orderservice.product.PurchaseResponse;

import java.util.List;

public class OrderMapper {
    private OrderMapper() {
    }

    public static Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public static OrderConfirmation toOrderConfirmation(@Valid OrderRequest request, CustomerResponse customer, List<PurchaseResponse> products) {
        return new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                products
        );
    }

    public static OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }

    public static PaymentRequest toPaymentRequest(OrderRequest request, CustomerResponse customer, Order order) {
        return new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                request.reference(),
                customer
        );
    }
}
