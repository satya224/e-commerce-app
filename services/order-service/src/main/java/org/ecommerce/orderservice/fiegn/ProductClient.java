package org.ecommerce.orderservice.fiegn;

import jakarta.validation.Valid;
import org.ecommerce.commonlib.product.Product;
import org.ecommerce.orderservice.product.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "product-service", path = "/api/v1/products")
public interface ProductClient {

    @PostMapping("/purchase")
    Optional<List<Product>> purchaseProducts(@RequestBody @Valid List<PurchaseRequest> request);

}
