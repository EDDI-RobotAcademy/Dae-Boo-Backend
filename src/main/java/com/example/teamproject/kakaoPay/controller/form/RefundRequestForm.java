package com.example.teamproject.kakaoPay.controller.form;

import com.example.teamproject.purchase.entity.RefundPurchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RefundRequestForm {
    private Long purchaseId;

    public RefundRequestForm(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
    public RefundPurchase toPurchase(RefundRequestForm form){
        return new RefundPurchase(form.purchaseId);
    }

}
