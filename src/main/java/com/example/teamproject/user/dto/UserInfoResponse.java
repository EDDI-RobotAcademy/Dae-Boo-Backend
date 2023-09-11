package com.example.teamproject.user.dto;

import com.example.teamproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

    private String nickname;
    private String profileImage;
    private int age;
    private String gender;
    private String mobile;
    private String email;

    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(
                user.getNickname(),
                user.getProfile_image(),
                Integer.parseInt(user.getAge()),
                user.getGender(),
                user.getMobile(),
                user.getEmail()
        );
    }
}
