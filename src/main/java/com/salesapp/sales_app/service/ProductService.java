package com.salesapp.sales_app.service;

import com.salesapp.sales_app.entity.Product;
import com.salesapp.sales_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Create a new product
    public Product createProduct(Product product) {
        product.setId(UUID.randomUUID());
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(UUID productId, Product productDetails) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setUpdatedAt(productDetails.getUpdatedAt());
        product.setUpdatedBy(productDetails.getUpdatedBy());

        return productRepository.save(product);
    }

    // Delete a product
    public void deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }
}
