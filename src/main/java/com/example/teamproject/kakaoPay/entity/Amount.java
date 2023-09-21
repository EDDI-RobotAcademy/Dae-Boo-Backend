package com.example.teamproject.kakaoPay.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Amount {
    public Amount(int total, int tax_free, int tax, int point, int discount, int green_deposit) {
        this.total = total;
        this.tax_free = tax_free;
        this.tax = tax;
        this.point = point;
        this.discount = discount;
        this.green_deposit = green_deposit;
    }
    public Amount() {
        this.total = total;
        this.tax_free = tax_free;
        this.tax = tax;
        this.point = point;
        this.discount = discount;
        this.green_deposit = green_deposit;
    }

    private int total; // 총 결제 금액
    private int tax_free; // 비과세 금액
    private int tax; // 부가세 금액
    private int point; // 사용한 포인트
    private int discount; // 할인금액
    private int green_deposit; // 컵 보증금
}