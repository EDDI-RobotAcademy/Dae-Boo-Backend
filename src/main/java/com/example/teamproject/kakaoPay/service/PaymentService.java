package com.example.teamproject.kakaoPay.service;

import com.example.teamproject.kakaoPay.dto.KakaoReadyResponse;
import com.example.teamproject.purchase.entity.RefundPurchase;

public interface PaymentService {

    KakaoReadyResponse kakaoPayReady(Long purchaseId);

    Boolean ApproveResponse(String pgToken);

    Boolean kakaoCancel(Long purchaseId);

}
