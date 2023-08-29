package com.example.teamproject.board_management.entity;

public enum CardCategory {
    DAILY("일상"),
    REVIEW("후기");

    private String category;

    CardCategory(String category) {
        this.category = category;
    }

    public String getCardCategory() {
        return category;
    }
}
