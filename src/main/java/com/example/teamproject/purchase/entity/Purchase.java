package com.example.teamproject.purchase.entity;

import com.example.teamproject.product.entity.Product;
import com.example.teamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private Integer purchaseAmount; // 수량

    private String userAccount; // 주소

    private LocalDateTime purchaseCreatedDate = LocalDateTime.now(); // 주문시간

    @JoinColumn(name = "productId")
    @ManyToOne
    private Product productId;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User userId;

    private Boolean paymentCheck = false;

    public Purchase(Integer purchaseAmount, String userAccount, Product productId, User userId) {
        this.purchaseAmount = purchaseAmount;
        this.userAccount = userAccount;
        this.productId = productId;
        this.userId = userId;
    }
}