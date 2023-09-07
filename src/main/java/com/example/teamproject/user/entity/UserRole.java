package com.example.teamproject.user.entity;

public enum UserRole {
    NORMAL("일반"),
    MANAGER("관리자");

    private String role;

    UserRole(String role) {this.role = role;}

    public String getUserRole() {return role;}
}
