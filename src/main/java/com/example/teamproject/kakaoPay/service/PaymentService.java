package com.example.teamproject.kakaoPay.service;

import com.example.teamproject.kakaoPay.controller.form.RefundRequestForm;
import com.example.teamproject.kakaoPay.dto.KakaoApproveResponse;
import com.example.teamproject.kakaoPay.dto.KakaoCancelResponse;
import com.example.teamproject.kakaoPay.dto.KakaoReadyResponse;

public interface PaymentService {

    KakaoReadyResponse kakaoPayReady(Long purchaseId);

    Boolean ApproveResponse(String pgToken);

    Boolean kakaoCancel(Long PurchaseId);

}
