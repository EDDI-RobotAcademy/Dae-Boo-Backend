package com.example.teamproject.board_management.entity;

public enum BoardCategory {
    DAILY("일상"),
    REVIEW("후기");

    private String category;

    BoardCategory(String category) {
        this.category = category;
    }

    public String getBoardCategory() {
        return category;
    }
}
