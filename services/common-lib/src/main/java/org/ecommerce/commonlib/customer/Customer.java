package org.ecommerce.commonlib.customer;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
