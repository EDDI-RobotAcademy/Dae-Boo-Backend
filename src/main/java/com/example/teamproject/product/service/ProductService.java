package com.example.teamproject.product.service;

import com.example.teamproject.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> retrieveAll();
}
