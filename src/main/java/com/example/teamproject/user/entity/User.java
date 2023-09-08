package com.example.teamproject.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String nickname;
    private String profile_image;
    private String age;
    private String gender;
    private String mobile;
    private String email;
    private List<String> interests;
    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate = true;
    private UserRole role = UserRole.NORMAL;

    public User(String nickname, String email, String profileImage, String gender, String ageRange) {
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profileImage;
        this.gender = gender;
        this.age = ageRange;
    }

    public void modify(String nickname, String mobile) {
        this.nickname = nickname;
        this.mobile = mobile;
    }
}
