package com.example.teamproject.user.dto;

import com.example.teamproject.user.entity.UserRole;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountResponse {
    private Long userId;
    private String nickname;
    private String age;
    private String gender;
    private String mobile;
    private String email;
    private UserRole role;

    public AccountResponse(Long userId, String nickname, String age, String gender, String mobile, String email, UserRole role) {
        this.userId = userId;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.role = role;
    }
}
