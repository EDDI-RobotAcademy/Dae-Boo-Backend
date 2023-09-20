package com.example.teamproject.product.controller;

import com.example.teamproject.product.dto.ProductDetailResponse;
import com.example.teamproject.product.dto.ProductModifyRequest;
import com.example.teamproject.product.dto.ProductRegisterRequest;
import com.example.teamproject.product.dto.ProductResponse;
import com.example.teamproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // 상품 등록 API
    @PostMapping("/product")
    public ProductDetailResponse registerProduct (@RequestBody ProductRegisterRequest request) {
        return productService.register(request);
    }

    // 상품 수정 API
    @PutMapping("/product/{id}")
    public ProductDetailResponse modifyProduct(
            @PathVariable(name = "id") long productId,
            @RequestBody ProductModifyRequest request
    ) {
        return productService.modify(productId, request);
    }

    // 상품 삭제 API
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable(name = "id") long productId) {
        productService.delete(productId);
    }

}
