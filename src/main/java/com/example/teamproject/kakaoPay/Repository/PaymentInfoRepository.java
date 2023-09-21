package com.example.teamproject.kakaoPay.Repository;

import com.example.teamproject.kakaoPay.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
    Optional<PaymentInfo> findByPurchaseId(Long purchaseId);
}
