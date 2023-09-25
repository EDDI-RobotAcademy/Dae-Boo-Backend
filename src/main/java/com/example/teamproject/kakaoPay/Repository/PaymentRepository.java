package com.example.teamproject.kakaoPay.Repository;

import com.example.teamproject.kakaoPay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTid(String tid);
}
