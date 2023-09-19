package com.example.teamproject.purchase.service;

import com.example.teamproject.purchase.controller.form.PurchaseForm;
import com.example.teamproject.purchase.entity.Purchase;

public interface PurchaseService {

    Purchase newPurchase(PurchaseForm form);
}
