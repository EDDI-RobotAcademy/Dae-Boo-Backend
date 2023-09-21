package com.example.teamproject.kakaoPay.controller.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RefundRequestForm {
    private final Long userId;
    private final Long purchaseId;
}
