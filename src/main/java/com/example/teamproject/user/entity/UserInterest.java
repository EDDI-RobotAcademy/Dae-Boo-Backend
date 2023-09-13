package com.example.teamproject.user.entity;

public enum UserInterest {
    INTEREST1("관심사1"),
    INTEREST2("관심사2"),
    INTEREST3("관심사3"),
    INTEREST4("관심사4");

    private String interest;


    UserInterest(String interest) {
        this.interest = interest;
    }
}
