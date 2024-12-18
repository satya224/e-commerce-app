package org.ecommerce.orderservice.order;

import jakarta.validation.Valid;
import org.ecommerce.commonlib.customer.Customer;
import org.ecommerce.commonlib.order.OrderConfirmation;
import org.ecommerce.commonlib.product.Product;
import org.ecommerce.orderservice.payment.PaymentRequest;

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

    public static OrderConfirmation toOrderConfirmation(@Valid OrderRequest request, Customer customer, List<Product> products) {
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

    public static PaymentRequest toPaymentRequest(OrderRequest request, Customer customer, Order order) {
        return new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                request.reference(),
                customer
        );
    }
}
