package com.example.teamproject.kakaoPay.controller;

import com.example.teamproject.kakaoPay.dto.KakaoApproveResponse;
import com.example.teamproject.kakaoPay.dto.KakaoReadyResponse;
import com.example.teamproject.kakaoPay.service.PaymentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public KakaoReadyResponse readyToKakaoPay() {

        return paymentService.kakaoPayReady();
    }
    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam(name = "pg_token") String pgToken) {


        KakaoApproveResponse kakaoApprove = paymentService.ApproveResponse(pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    /**
     * 결제 진행 중 취소
     */
//    @GetMapping("/cancel")
//    public void cancel() {
//
//        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
//    }

    /**
     * 결제 실패
     */
//    @GetMapping("/fail")
//    public void fail() {
//
//        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
//    }
}