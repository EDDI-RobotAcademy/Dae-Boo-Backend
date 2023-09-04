package com.example.teamproject.user.service;

import com.example.teamproject.user.dto.KakaoOAuthToken;
import com.example.teamproject.user.dto.NaverOAuthToken;
import com.example.teamproject.user.entity.User;
import org.springframework.http.HttpHeaders;

public interface UserService {
    String getAuthorizeCode();

    NaverOAuthToken generateAccessToken(String code);

    User getNaverUserInfo(String accessToken, HttpHeaders headers);

    String getKakaoAuthorizeCode();

    KakaoOAuthToken kakaoCallback(String code);

    User findUserByUserId(Long userId);

    // 회원 정보 조회
    User getUserInfo(Long userId);
}
