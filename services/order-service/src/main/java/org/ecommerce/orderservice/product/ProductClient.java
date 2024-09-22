package org.ecommerce.orderservice.product;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @PostMapping("/purchase")
    Optional<List<PurchaseResponse>> purchaseProducts(@RequestBody @Valid List<PurchaseRequest> request);

}
