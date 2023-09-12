package com.example.teamproject.user.service;

import com.example.teamproject.user.dto.AccountResponse;
import com.example.teamproject.user.dto.UserInfoModifyRequest;
import com.example.teamproject.user.dto.UserInfoResponse;
import com.example.teamproject.user.entity.User;

import java.util.List;

public interface UserService {


    User findUserByUserId(Long userId);

    // 회원 정보 조회
    User getUserInfo(Long userId);

    List<User> userList();

    User stopUser(Long id);

    UserInfoResponse modify(Long userId, UserInfoModifyRequest request);

    void delete(Long userId);

    AccountResponse findAccountInfoById(Long accountId);
}
