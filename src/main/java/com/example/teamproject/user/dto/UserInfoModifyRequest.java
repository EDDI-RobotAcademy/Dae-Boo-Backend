package com.example.teamproject.user.dto;

import com.example.teamproject.user.entity.Interest;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.entity.UserInterest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoModifyRequest {
    private String nickname;
    private String mobile;
    private String email;
    private Interest interest1;
    private Interest interest2;

    public static UserInfoModifyRequest from(User user) {
        return new UserInfoModifyRequest(
                user.getNickname(),
                user.getMobile(),
                user.getEmail(),
                user.getInterest1(),
                user.getInterest2()
        );
    }
}
