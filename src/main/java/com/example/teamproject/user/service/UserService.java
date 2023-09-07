package com.example.teamproject.user.service;

import com.example.teamproject.user.dto.KakaoOAuthToken;
import com.example.teamproject.user.dto.NaverOAuthToken;
import com.example.teamproject.user.entity.User;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface UserService {


    User findUserByUserId(Long userId);

    // 회원 정보 조회
    User getUserInfo(Long userId);

    List<User> userList();

    Boolean stopUser(Long id);
}
