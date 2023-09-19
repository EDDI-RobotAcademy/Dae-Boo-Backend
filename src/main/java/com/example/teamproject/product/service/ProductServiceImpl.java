package com.example.teamproject.product.service;

import com.example.teamproject.product.dto.ProductResponse;
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
}