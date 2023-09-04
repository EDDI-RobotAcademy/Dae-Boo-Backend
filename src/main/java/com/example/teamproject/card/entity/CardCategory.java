package com.example.teamproject.card.entity;

public enum CardCategory {
    A("여가"),
    B("쇼핑"),
    C("레저");

    private String category;

    CardCategory(String category) {
        this.category = category;
    }

    public String getCardCategory() {
        return category;
    }

}
