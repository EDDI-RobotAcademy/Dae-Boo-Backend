package com.example.teamproject.kakaoPay.service;


import com.example.teamproject.kakaoPay.dto.KakaoApproveResponse;
import com.example.teamproject.kakaoPay.dto.KakaoReadyResponse;
import com.example.teamproject.product.entity.Product;
import com.example.teamproject.product.repository.ProductRepository;
import com.example.teamproject.utility.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService{

    final private PropertyUtil propertyUtil;
    static final String cid = "TC0ONETIME";

    final String admin_Key = propertyUtil.getProperty("admin_key") ;
    private KakaoReadyResponse kakaoReady;
    private final ProductRepository productRepository;
    public KakaoReadyResponse kakaoPayReady() {
        Optional<Product> maybeProduct = productRepository.findById(1L);
        if (maybeProduct.isEmpty()){
            return null;
        }
        Product prod = maybeProduct.get();
        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "partner_order_id");
        parameters.add("partner_user_id", "partner_user_id");
        parameters.add("item_name", prod.getProductName());
        parameters.add("quantity", "1");
        parameters.add("total_amount", prod.getProductPrice());
        parameters.add("vat_amount", "35");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", propertyUtil.getProperty("spring.web.cors.allowed-origins")+"/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", propertyUtil.getProperty("spring.web.cors.allowed-origins")+"/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", propertyUtil.getProperty("spring.web.cors.allowed-origins")+"/payment/fail"); // 실패 시 redirect url

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);

        return kakaoReady;
    }


    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
    public KakaoApproveResponse ApproveResponse(String pgToken) {

        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);

        return approveResponse;
    }
}