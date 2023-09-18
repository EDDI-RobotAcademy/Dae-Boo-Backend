package com.example.teamproject.kakaoPay.exception;

public class ProductNotFoundException extends RuntimeException {

    private final Long productId;

    public ProductNotFoundException(Long productId) {
        super("Product not found with ID: " + productId);
        this.productId = productId;
    }

    public ProductNotFoundException(String productId) {
        super("Product not found with ID: " + productId);
        this.productId = Long.parseLong(productId);
    }

    public Long getProductId() {
        return productId;
    }
}
