package com.example.teamproject.user.controller.form;

import com.example.teamproject.user.dto.NaverOAuthToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class LoginUserEmailForm {
    final private NaverOAuthToken naverOAuthToken;
    final private Long userId;
}
