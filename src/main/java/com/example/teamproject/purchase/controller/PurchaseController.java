package com.example.teamproject.purchase.controller;

import com.example.teamproject.kakaoPay.controller.form.RefundRequestForm;
import com.example.teamproject.purchase.controller.form.PurchaseForm;
import com.example.teamproject.purchase.entity.Purchase;
import com.example.teamproject.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    //관리자에게 환불 요청 리스트를 반환합니다.
    @PostMapping("/refundList")
    public List<Purchase> refundList(){
        return purchaseService.refundList();
    }
    // 모든 주문 리스트 리턴
    @PostMapping("/list")
    public List<Purchase> list(){
        return purchaseService.list();
    }

    // 관리자 메인 페이지 -  총 판매량 개수 가져오기
    @GetMapping("/manager/main-page/total-salesNum")
    public Integer requestTotalSalesNumber() {
        log.info("requestTotalSalesNumber()");
        return purchaseService.totalSalesNumber();
    }

    @GetMapping("/manager/main-page/apply-refundNum")
    public Integer requestTotalApplyRefundNumber() {
        log.info("requestTotalApplyRefundNumber()");
        return purchaseService.totalRefundNumber();
    }
}
