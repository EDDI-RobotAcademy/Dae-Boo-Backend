package com.example.teamproject.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Product")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private int productPrice;
    private String description;
    private String image;
    private boolean isDeleted = false;

    public Product(String productName, int productPrice, String description, String image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.image = image;
    }
}
