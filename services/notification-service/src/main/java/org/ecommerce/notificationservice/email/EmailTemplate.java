package org.ecommerce.notificationservice.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {

    PAYMENT_CONFIRMATION("payment_confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order_confirmation.html", "Order Confirmation");

    private final String template;

    private final String subject;

    }
