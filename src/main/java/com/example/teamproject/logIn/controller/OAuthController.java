package com.example.teamproject.logIn.controller;

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
    @GetMapping("/naver/login")
    public String requestNaverAuthorizeCode () {
        log.info("requestGithubAuthorizeCode()");
        return userService.getAuthorizeCode();
    }
    @GetMapping("/naver/oauth-code")
    public NaverOAuthToken getNaverUserInfo(@RequestParam String code) {
        log.info("naverCallback()");
        log.info(code);
        return userService.generateAccessToken(code);
    }
}
