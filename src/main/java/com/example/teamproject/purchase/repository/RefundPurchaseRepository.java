package com.example.teamproject.purchase.repository;

import com.example.teamproject.purchase.entity.RefundPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefundPurchaseRepository extends JpaRepository<RefundPurchase, Long> {
    Optional<RefundPurchase> findByPurchaseId(Long purchaseId);
}
