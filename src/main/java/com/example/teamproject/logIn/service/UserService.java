package com.example.teamproject.logIn.service;

import com.example.teamproject.logIn.dto.NaverOAuthToken;
import com.example.teamproject.logIn.entity.User;
import org.springframework.http.HttpHeaders;

public interface UserService {
    String getAuthorizeCode();

    NaverOAuthToken generateAccessToken(String code);

    User getNaverUserInfo(String accessToken, HttpHeaders headers);

}
