package com.example.teamproject.logIn.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverOauthAccountInfoResponse {


    @JsonProperty("name")
    private String name;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("profile_image")
    private String profile_image;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birthyear")
    private String birthyear;

    @JsonProperty("mobile")
    private String mobile;
}
