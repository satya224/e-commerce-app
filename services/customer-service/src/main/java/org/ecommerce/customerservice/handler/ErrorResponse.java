package org.ecommerce.customerservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
