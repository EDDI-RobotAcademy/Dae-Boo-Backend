package com.example.teamproject.purchase.controller;

import com.example.teamproject.kakaoPay.controller.form.RefundRequestForm;
import com.example.teamproject.purchase.controller.form.PurchaseForm;
import com.example.teamproject.purchase.entity.Purchase;
import com.example.teamproject.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {
    final private PurchaseService purchaseService;

    @PostMapping("/register")
    public Purchase purchaseRegister (@RequestBody PurchaseForm form){
       return purchaseService.newPurchase(form);
    }
    // 사용자가 환불 요청 시 환불 목록에 추가하는 엔드포인트
    @PostMapping("/cancel")
    public Boolean requestCancel(@RequestBody RefundRequestForm form){
        return purchaseService.requestRefund(form.toPurchase(form));
    }

}
