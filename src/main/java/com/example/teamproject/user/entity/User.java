package com.example.teamproject.user.entity;

import com.example.teamproject.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private String age;
    private String gender;
    private String mobile;
    private String email;
    private UserInterest interest1;
    private UserInterest interest2;
    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate = true;
    private UserRole role = UserRole.NORMAL;

    public User(String nickname, String email, String gender, String ageRange) {
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = ageRange;
    }

    public void modify(String nickname, String mobile, String email, UserInterest interest1, UserInterest interest2) {
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.interest1 = interest1;
        this.interest2 = interest2;
    }

    public void softDelete() {
        this.activate = false;
    }

    public void setInterests(UserInterest interest1, UserInterest interest2) {
        this.interest1 = interest1;
        this.interest2 = interest2;
    }
}
