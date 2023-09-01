package com.example.teamproject.user.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NaverOauthTokenRequest {
    @JsonProperty("naver_client_id")
    private String clientId;

    @JsonProperty("naver_client_secret")
    private String clientSecret;

    private String code;
}
