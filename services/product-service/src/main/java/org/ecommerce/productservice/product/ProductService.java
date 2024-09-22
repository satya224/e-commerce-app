package org.ecommerce.productservice.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ecommerce.productservice.exception.ProductPurchaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Integer createProduct(ProductRequest request) {
        Product product = ProductMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    @Transactional
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        List<Product> products = productRepository.findAllByIdInOrderById(productIds);

        if (products.size() != productIds.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        List<ProductPurchaseRequest> sortedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            ProductPurchaseRequest purchaseRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < purchaseRequest.quantity()) {
                throw new ProductPurchaseException("Product with ID:: " + product.getId() + " does not have enough quantity");
            }
            product.setAvailableQuantity(product.getAvailableQuantity() - purchaseRequest.quantity());
            purchasedProducts.add(ProductMapper.toProductPurchaseResponse(product, purchaseRequest.quantity()));
        }

        productRepository.saveAll(products);

        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(ProductMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toProductResponse)
                .toList();
    }
}
