package com.example.teamproject.purchase.controller;

import com.example.teamproject.purchase.controller.form.PurchaseForm;
import com.example.teamproject.purchase.entity.Purchase;
import com.example.teamproject.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {
    final private PurchaseService purchaseService;
    @PostMapping("/register")
    public Purchase purchaseRegister (@RequestBody PurchaseForm form){
       return purchaseService.newPurchase(form);
    }

}
