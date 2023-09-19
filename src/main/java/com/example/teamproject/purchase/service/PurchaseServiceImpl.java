package com.example.teamproject.purchase.service;

import com.example.teamproject.product.entity.Product;
import com.example.teamproject.product.repository.ProductRepository;
import com.example.teamproject.purchase.controller.form.PurchaseForm;
import com.example.teamproject.purchase.entity.Purchase;
import com.example.teamproject.purchase.repository.PurchaseRepository;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Purchase newPurchase(PurchaseForm form) {
        Optional<Product> maybeProduct = productRepository.findById(form.getProductId());
        Optional< User> maybeUser = userRepository.findByUserId(form.getUserId());
        if (maybeProduct.isPresent()){
            if (maybeUser.isPresent()){

                Purchase purchase = purchaseRepository.save(new Purchase(form.getAmount(),form.getUserAccount(),maybeProduct.get(),maybeUser.get()));
                return purchase;
            }
        }
        return null;

    }
}
