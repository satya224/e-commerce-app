package org.ecommerce.paymentservice.payment;

import org.ecommerce.paymentservice.kafka.PaymentNotificationRequest;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
    }

    public static PaymentNotificationRequest toPaymentNotificationRequest(PaymentRequest request) {
        return new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        );
    }
}
