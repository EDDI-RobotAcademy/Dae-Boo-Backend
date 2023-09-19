package com.example.teamproject.purchase.controller.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PurchaseForm {
    private Long userId;
    private Long productId;
    private String userAccount;
    private int amount;

}
