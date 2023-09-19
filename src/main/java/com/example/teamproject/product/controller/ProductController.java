package com.example.teamproject.product.controller;

import com.example.teamproject.product.dto.ProductDetailResponse;
import com.example.teamproject.product.dto.ProductResponse;
import com.example.teamproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 리스트 API
    @GetMapping("/products")
    public List<ProductResponse> retrieveProductList() {
        return productService.retrieveAll();
    }

    // 상품 상세 조회 API
    @GetMapping("/product/{id}")
    public ProductDetailResponse retrieveProductDetail(@PathVariable(name = "id") long productId) {
        return productService.retrieve(productId);
    }
}
