package com.example.teamproject.kakaoPay.controller;

import com.example.teamproject.kakaoPay.controller.form.RefundRequestForm;
import com.example.teamproject.kakaoPay.dto.KakaoApproveResponse;
import com.example.teamproject.kakaoPay.dto.KakaoCancelResponse;
import com.example.teamproject.kakaoPay.dto.KakaoReadyResponse;
import com.example.teamproject.kakaoPay.service.PaymentService;
import com.example.teamproject.purchase.entity.Purchase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j

public class PaymentController {
    private final PaymentService paymentService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public KakaoReadyResponse readyToKakaoPay(@RequestBody Purchase request) {

        return paymentService.kakaoPayReady(request.getPurchaseId());
    }
    @PostMapping("/success")
    public Boolean afterPayRequest(@RequestBody String token) {


        Boolean kakaoApprove = paymentService.ApproveResponse(token);


        return kakaoApprove;
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public ResponseEntity<Boolean> cancel() {

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public ResponseEntity<Boolean> fail() {
        // 결제가 실패했을 때 true 반환
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @PostMapping("/refund")
    public Boolean refund(@RequestBody RefundRequestForm form) {
        Boolean kakaoCancelResponse = paymentService.kakaoCancel(form.getPurchaseId());
        log.info(kakaoCancelResponse.toString());
        return kakaoCancelResponse;
    }

}