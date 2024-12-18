package org.ecommerce.notificationservice.notification;

import lombok.Builder;
import lombok.Data;
import org.ecommerce.commonlib.order.OrderConfirmation;
import org.ecommerce.commonlib.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
