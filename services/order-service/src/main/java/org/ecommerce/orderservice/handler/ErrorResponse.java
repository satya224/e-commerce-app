package org.ecommerce.orderservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
