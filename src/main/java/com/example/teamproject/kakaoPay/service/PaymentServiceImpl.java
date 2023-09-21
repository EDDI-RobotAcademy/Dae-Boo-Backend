package com.example.teamproject.kakaoPay.service;

import com.example.teamproject.kakaoPay.controller.form.RefundRequestForm;
import com.example.teamproject.kakaoPay.dto.KakaoApproveResponse;
import com.example.teamproject.kakaoPay.dto.KakaoCancelResponse;
import com.example.teamproject.kakaoPay.dto.KakaoReadyResponse;
import com.example.teamproject.kakaoPay.exception.ProductNotFoundException;
import com.example.teamproject.product.entity.Product;
import com.example.teamproject.product.repository.ProductRepository;
import com.example.teamproject.purchase.entity.Purchase;
import com.example.teamproject.purchase.repository.PurchaseRepository;
import com.example.teamproject.utility.PropertyUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService{
    private final ProductRepository productRepository;
    private final PropertyUtil propertyUtil;
    private final PurchaseRepository purchaseRepository;
//    private final PaymentDataRepository paymentDataRepository;
    private final String cid = "TC0ONETIME";

    public KakaoReadyResponse kakaoReady;

    private HttpHeaders getHeaders() {
        final String ADMIN_KEY = propertyUtil.getProperty("admin_key") ;

        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + ADMIN_KEY;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }

    public KakaoReadyResponse kakaoPayReady(Long purchaseId) {
        Optional<Purchase> maybePurchase = purchaseRepository.findById(purchaseId);


        if (maybePurchase.isPresent()) {
            MultiValueMap<String, String> parameters = kakaoPayRequest(maybePurchase.get());

            // 파라미터, 헤더
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, getHeaders());

            // 외부에 보낼 url
            RestTemplate restTemplate = new RestTemplate();

            kakaoReady = restTemplate.postForObject(
                    "https://kapi.kakao.com/v1/payment/ready",
                    requestEntity,
                    KakaoReadyResponse.class);
            log.info(kakaoReady.toString());
            return kakaoReady;
        } else {
            // Product가 없는 경우 예외 처리 또는 다른 방식으로 처리
            throw new ProductNotFoundException("Product with ID 1 not found");
        }
    }

    private MultiValueMap<String, String> kakaoPayRequest(Purchase form) {
        Product prod = form.getProductId();

        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "test");
        parameters.add("partner_user_id", "test");
        parameters.add("item_name", prod.getProductName());
        parameters.add("quantity", String.valueOf(form.getPurchaseAmount()));
        parameters.add("total_amount", String.valueOf(prod.getProductPrice()* form.getPurchaseAmount()));
        parameters.add("vat_amount", "35");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", getApprovalUrl());
        parameters.add("cancel_url", getCancelUrl());
        parameters.add("fail_url", getFailUrl());
        return parameters;
    }

    // 리다이렉트 URL 값을 프로퍼티 파일에서 가져오는 메서드 예시
    private String getApprovalUrl() {
        return propertyUtil.getProperty("approve_url") + "payment/success";
    }

    private String getCancelUrl() {
        return propertyUtil.getProperty("approve_url") + "payment/cancel";
    }

    private String getFailUrl() {
        return propertyUtil.getProperty("approve_url") + "payment/fail";
    }


//    public KakaoCancelResponse kakaoCancel(RefundRequestForm form) {
//
//        // 카카오페이 요청
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("cid", cid);
//        parameters.add("tid", "환불할 결제 고유 번호");
//        parameters.add("cancel_amount", "환불 금액");
//        parameters.add("cancel_tax_free_amount", "환불 비과세 금액");
//        parameters.add("cancel_vat_amount", "환불 부가세");
//
//        // 파라미터, 헤더
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
//
//        // 외부에 보낼 url
//        RestTemplate restTemplate = new RestTemplate();
//
//        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
//                "https://kapi.kakao.com/v1/payment/cancel",
//                requestEntity,
//                KakaoCancelResponse.class);
//
//        return cancelResponse;
//    }
    public Boolean ApproveResponse(String pgToken) {

        String originalString = pgToken;
        String stringWithoutLastCharacter = originalString.substring(0, originalString.length() - 1);


        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "test");
        parameters.add("partner_user_id", "test");
        parameters.add("pg_token", stringWithoutLastCharacter);
        log.info(parameters.toString());

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);
        if (approveResponse.getAid().isEmpty()){
            return false;
        }
//        PaymentData paymentData = new PaymentData(approveResponse.getAid(),approveResponse.getTid(),approveResponse.getPayment_method_type(), approveResponse.getAmount());
//        paymentDataRepository.save(paymentData);
//        log.info(paymentData.toString());


        return true;
    }

}