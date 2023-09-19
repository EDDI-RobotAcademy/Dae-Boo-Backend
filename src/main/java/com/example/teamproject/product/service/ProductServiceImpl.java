package com.example.teamproject.product.service;

import com.example.teamproject.product.dto.ProductDetailResponse;
import com.example.teamproject.product.dto.ProductModifyRequest;
import com.example.teamproject.product.dto.ProductRegisterRequest;
import com.example.teamproject.product.dto.ProductResponse;
import com.example.teamproject.product.entity.Product;
import com.example.teamproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> retrieveAll() {
        return productRepository.findAllByIsDeletedFalse().stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Override
    public ProductDetailResponse retrieve(long productId) {
        return productRepository.findByProductIdAndIsDeletedFalse(productId)
                .map(ProductDetailResponse::from)
                .orElseThrow(() -> new RuntimeException("해당 상품에 대한 정보를 찾을 수 없습니다."));
    }

    @Override
    public void register(ProductRegisterRequest request) {
        Product product = request.toEntity();
        productRepository.save(product);
    }

    @Override
    public void modify(long productId, ProductModifyRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 상품입니다."));
        product.modifyProductInfo(request);
        productRepository.save(product);
    }
}