package com.example.teamproject.card.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Entity
@Setter
@Table(name = "Card")
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "fee")
    private String fee;

    @Column(name = "card_condition") // 열 이름 변경
    private String cardCondition; // 열 이름 변경

    @Column(name = "benefit")
    private String benefit;

    @Column(name = "card_image")
    private String cardImage; // 열 이름 변경

    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate = true;

    private long viewCount = 0;

    public Card(String name, String company, String fee, String cardCondition, String benefit, String cardImage) {
        this.name = name;
        this.company = company;
        this.fee = fee;
        this.cardCondition = cardCondition;
        this.benefit = benefit;
        this.cardImage = cardImage;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }


    // Getter와 Setter 메서드는 여기에 추가
}