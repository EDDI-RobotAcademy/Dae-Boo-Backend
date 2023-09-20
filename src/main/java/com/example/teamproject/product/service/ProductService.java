package com.example.teamproject.product.service;

import com.example.teamproject.product.dto.ProductDetailResponse;
import com.example.teamproject.product.dto.ProductModifyRequest;
import com.example.teamproject.product.dto.ProductRegisterRequest;
import com.example.teamproject.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> retrieveAll();

    ProductDetailResponse retrieve(long productId);

    void register(ProductRegisterRequest request);

    void modify(long productId, ProductModifyRequest request);

    void delete(long productId);
}
