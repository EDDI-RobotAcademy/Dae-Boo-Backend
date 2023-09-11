package com.example.teamproject.user.dto;

import com.example.teamproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoModifyRequest {
    private String nickname;
    private String mobile;
    private String email;

    public static UserInfoModifyRequest from(User user) {
        return new UserInfoModifyRequest(
                user.getNickname(),
                user.getMobile(),
                user.getEmail()
        );
    }
}
