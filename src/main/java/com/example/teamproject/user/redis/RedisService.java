package com.example.teamproject.user.redis;

public interface RedisService {

    void setKeyAndValue(String token, Long accountId);
    Long getValueByKey(String token);
    boolean deleteByKey(String userToken);
}
