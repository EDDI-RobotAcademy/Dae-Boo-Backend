package com.example.teamproject.user.service;

import com.example.teamproject.user.controller.form.LoginUserEmailForm;
import com.example.teamproject.user.dto.KakaoOAuthToken;
import com.example.teamproject.user.dto.NaverOAuthToken;
import com.example.teamproject.user.entity.User;
import org.springframework.http.HttpHeaders;

public interface OauthService {

    String getAuthorizeCode();

    String generateAccessToken(String code);

    User getNaverUserInfo(String accessToken, HttpHeaders headers);

    String getKakaoAuthorizeCode();

    String kakaoCallback(String code);
}
