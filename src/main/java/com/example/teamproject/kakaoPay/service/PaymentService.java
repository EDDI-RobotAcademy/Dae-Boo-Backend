package com.example.teamproject.kakaoPay.service;

import com.example.teamproject.kakaoPay.dto.KakaoApproveResponse;
import com.example.teamproject.kakaoPay.dto.KakaoReadyResponse;

public interface PaymentService {

    KakaoReadyResponse kakaoPayReady();

    KakaoApproveResponse ApproveResponse(String pgToken);

}
