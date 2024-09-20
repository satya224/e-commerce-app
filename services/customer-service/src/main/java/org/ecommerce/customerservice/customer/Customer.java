package org.ecommerce.customerservice.customer;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
