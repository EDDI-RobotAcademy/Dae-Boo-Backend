package com.example.teamproject.user.controller;

import com.example.teamproject.user.controller.form.LoginUserEmailForm;
import com.example.teamproject.user.dto.KakaoOAuthToken;
import com.example.teamproject.user.dto.NaverOAuthToken;
import com.example.teamproject.user.service.OauthService;
import com.example.teamproject.user.service.UserService;
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
    final private OauthService oauthService;

    // NAVER OAuth
    @GetMapping("/naver/login")
    public String requestNaverAuthorizeCode () {
        log.info("requestNaverAuthorizeCode()");
        return oauthService.getAuthorizeCode();
    }
    @GetMapping("/naver/oauth-code")
    public LoginUserEmailForm getNaverUserInfo(@RequestParam String code) {
        log.info("naverCallback()");
        log.info(code);
        return oauthService.generateAccessToken(code);
    }


    // KAKAO OAuth
    @GetMapping("/kakao/login")
    public String requestKakaoAuthorizeCode() {
        log.info("requestKakaoAuthorizeCode");
        return oauthService.getKakaoAuthorizeCode();
    }

    @GetMapping("/kakao/callback")
    public String kakaoCallback(@RequestParam("code") String code) {
        log.info("카카오 코드를 받았습니다. 토큰 요청을 하겠습니다 !");
        return oauthService.kakaoCallback(code);
    }


}
