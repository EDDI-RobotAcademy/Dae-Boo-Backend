package com.example.teamproject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModifyRequest {

    private String productName;
    private int price;
    private String description;
    private String image;
}
