package com.example.teamproject.kakaoPay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PaymentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String aid;
    private final String tid;
    private final String cid="TC0ONETIME";

    private final String partner_order_id="test";
    private final String partner_user_id="test";
    private final String payment_method_type;
    private final Amount amount;


}
