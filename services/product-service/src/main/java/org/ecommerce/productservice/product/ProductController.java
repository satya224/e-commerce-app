package org.ecommerce.productservice.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request) {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequest> request) {
        return new ResponseEntity<>(productService.purchaseProducts(request), HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
