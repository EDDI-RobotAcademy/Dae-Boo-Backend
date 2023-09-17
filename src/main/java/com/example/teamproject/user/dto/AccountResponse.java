package com.example.teamproject.user.dto;

import com.example.teamproject.user.entity.UserInterest;
import com.example.teamproject.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class AccountResponse {
    private Long userId;
    private String nickname;
    private String age;
    private String gender;
    private String mobile;
    private String email;
    private UserRole role;
    private UserInterest interest1;
    private UserInterest interest2;
}
