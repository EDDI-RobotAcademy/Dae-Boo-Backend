package com.example.teamproject.product.dto;

import com.example.teamproject.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterRequest {

    private String productName;
    private int price;
    private String image;
    private String description;

    public Product toEntity() {
        return new Product(productName, price, description, image);
    }
}
