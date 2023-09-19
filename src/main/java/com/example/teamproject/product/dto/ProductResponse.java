package com.example.teamproject.product.dto;

import com.example.teamproject.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private long productId;
    private String name;
    private int price;
    private String image;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getImage()
        );
    }
}
