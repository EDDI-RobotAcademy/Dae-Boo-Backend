package com.example.teamproject.kakaoPay.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aid;
    private String tid;
    private String cid="TC0ONETIME";

    private String partner_order_id="test";
    private String partner_user_id="test";
    private String payment_method_type;
    private int totalAmount;

    public Payment(String aid, String tid, String payment_method_type, int totalAmount) {
        this.aid = aid;
        this.tid = tid;
        this.payment_method_type = payment_method_type;
        this.totalAmount = totalAmount;
    }
}
