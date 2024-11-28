package org.ecommerce.orderservice.fiegn;

import org.ecommerce.orderservice.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service", path = "/api/v1/customers")
public interface CustomerClient {

    @GetMapping("/{customerId}")
    Optional<CustomerResponse> findCustomerById(@PathVariable String customerId);

}
