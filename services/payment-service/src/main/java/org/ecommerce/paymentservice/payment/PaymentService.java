package org.ecommerce.paymentservice.payment;

import lombok.RequiredArgsConstructor;
import org.ecommerce.paymentservice.kafka.NotificationProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        Payment payment = paymentRepository.save(PaymentMapper.toPayment(request));

        notificationProducer.sendNotification(PaymentMapper.toPaymentNotificationRequest(request));

        return payment.getId();
    }
}
