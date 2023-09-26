package com.example.teamproject.purchase.service;

import com.example.teamproject.purchase.controller.form.PurchaseForm;
import com.example.teamproject.purchase.entity.Purchase;
import com.example.teamproject.purchase.entity.RefundPurchase;

import java.util.List;

public interface PurchaseService {

    Purchase newPurchase(PurchaseForm form);

    List<Purchase> refundList();

    Boolean requestRefund(RefundPurchase refundPurchase);

    List<Purchase> list();

    Integer totalSalesNumber();

    Integer totalRefundNumber();
}
