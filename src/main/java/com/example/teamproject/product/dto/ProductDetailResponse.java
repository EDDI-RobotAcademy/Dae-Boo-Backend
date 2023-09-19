package com.example.teamproject.product.dto;

import com.example.teamproject.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {

    private Long productId;
    private String name;
    private int price;
    private String description;
    private String image;

    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getDescription(),
                product.getImage()
        );
    }

}
