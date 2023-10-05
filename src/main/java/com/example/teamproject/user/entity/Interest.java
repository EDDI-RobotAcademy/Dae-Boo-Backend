package com.example.teamproject.user.entity;

public enum Interest {
    관리비("관리비"),
    교육("교육"),
    금융("금융"),
    대형마트("대형마트"),
    레저("레저"),
    렌탈("렌탈"),
    문화("문화"),
    베이커리("베이커리"),
    뷰티("뷰티"),
    쇼핑("쇼핑"),
    영화("영화"),
    외식("외식"),
    의료("의료"),
    주유("주유"),
    카페("카페"),
    통신("통신"),
    편의점("편의점");

    private final String value;

    Interest(String value) {
        this.value = value;
    }
}
