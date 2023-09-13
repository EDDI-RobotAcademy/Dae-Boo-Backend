package com.example.teamproject.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AfterLoginRequest {

    private String userToken;

    public AfterLoginRequest(String userToken) {
        this.userToken = userToken;
    }

    public AfterLoginRequest() {}
}
