package org.ecommerce.orderservice.order;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.orderservice.customer.CustomerClient;
import org.ecommerce.orderservice.customer.CustomerResponse;
import org.ecommerce.orderservice.exception.BusinessException;
import org.ecommerce.orderservice.kafka.OrderProducer;
import org.ecommerce.orderservice.orderline.OrderLineRequest;
import org.ecommerce.orderservice.orderline.OrderLineService;
import org.ecommerce.orderservice.payment.PaymentClient;
import org.ecommerce.orderservice.product.ProductClient;
import org.ecommerce.orderservice.product.PurchaseRequest;
import org.ecommerce.orderservice.product.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer (use openfeign to call the customer microservice)
        CustomerResponse customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: Customer not found with id: " + request.customerId()));

        // purchase the products --> product microservice

        List<PurchaseResponse> purchaseResponses = productClient.purchaseProducts(request.products())
                .orElseThrow(() -> new BusinessException("Cannot create order:: Error while purchasing products"));

        // persist the order

        Order order = orderRepository.save(OrderMapper.toOrder(request));

        // persist the order lines

        List<OrderLineRequest> orderLineRequests = new ArrayList<>();
        for(PurchaseRequest purchaseRequest : request.products()) {
            orderLineRequests.add(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        orderLineService.saveOrderLines(orderLineRequests);

        // start payment process
        paymentClient.requestOrderPayment(OrderMapper.toPaymentRequest(request, customer, order));

        // send the order confirmation --> notification microservice (kafka)
        orderProducer.sendOrderConfirmation(OrderMapper.toOrderConfirmation(request, customer, purchaseResponses));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(OrderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
    }
}

