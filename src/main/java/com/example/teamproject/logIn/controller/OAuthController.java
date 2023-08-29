package com.example.teamproject.logIn.controller;

import com.example.teamproject.logIn.dto.KakaoOAuthToken;
import com.example.teamproject.logIn.dto.NaverOAuthToken;
import com.example.teamproject.logIn.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class OAuthController {
    final private UserService userService;

    // NAVER OAuth
    @GetMapping("/naver/login")
    public String requestNaverAuthorizeCode () {
        log.info("requestNaverAuthorizeCode()");
        return userService.getAuthorizeCode();
    }
    @GetMapping("/naver/oauth-code")
    public NaverOAuthToken getNaverUserInfo(@RequestParam String code) {
        log.info("naverCallback()");
        log.info(code);
        return userService.generateAccessToken(code);
    }


    // KAKAO OAuth
    @GetMapping("/kakao/login")
    public String requestKakaoAuthorizeCode() {
        log.info("requestKakaoAuthorizeCode");
        return userService.getKakaoAuthorizeCode();
    }

    @GetMapping("/kakao/callback")
    public KakaoOAuthToken kakaoCallback(@RequestParam("code") String code) {
        log.info("카카오 코드를 받았습니다. 토큰 요청을 하겠습니다 !");
        return userService.kakaoCallback(code);
    }
}
