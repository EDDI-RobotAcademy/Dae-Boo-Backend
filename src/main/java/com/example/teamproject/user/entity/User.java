package com.example.teamproject.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String id;
    private String nickname;
    private String profile_image;
    private String age;
    private String gender;
    private String mobile;
    private String name;
    private String email;
    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate = true;

    public User(String nickname, String profile_image, String age, String gender, String mobile, String name, String id) {
        this.nickname = nickname;
        this.profile_image = profile_image;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
        this.name = name;
        this.id = id;
    }

    public User(String nickname, String email, String profileImage, String gender, String ageRange) {
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profileImage;
        this.gender = gender;
        this.age = ageRange;
    }
}
