package org.ecommerce.notificationservice.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.commonlib.product.Product;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

        final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("customerName", customerName);
        templateModel.put("amount", amount);
        templateModel.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(templateModel);
        helper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            helper.setTo(destinationEmail);
            helper.setText(html, true);
            javaMailSender.send(message);
            log.info("Sent payment success email to {} with template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.warn("Failed to send payment success email to {}", destinationEmail);
        }

    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

        final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("customerName", customerName);
        templateModel.put("amount", amount);
        templateModel.put("orderReference", orderReference);
        templateModel.put("products", products);

        Context context = new Context();
        context.setVariables(templateModel);
        helper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            helper.setTo(destinationEmail);
            helper.setText(html, true);
            javaMailSender.send(message);
            log.info("Sent order confirmation email to {} with template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.warn("Failed to send order confirmation email to {}", destinationEmail);
        }

    }
}
