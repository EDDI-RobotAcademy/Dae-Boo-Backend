package com.example.teamproject.card.entity;

public enum KoreanDecimalClassification {
    EATOUT("외식"),
    GAS("주유");

    private String classificationName;

    KoreanDecimalClassification(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getClassificationName() {
        return classificationName;
    }

}
