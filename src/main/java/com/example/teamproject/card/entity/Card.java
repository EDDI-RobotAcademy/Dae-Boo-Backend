package com.example.teamproject.card.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
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
    private String card_image;

    @Column(name = "character_image")
    private Boolean characterImage; // 열 이름 변경

    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate = true;

    public Card(String name, String company, String fee, String cardCondition, String benefit, Boolean characterImage) {
        this.name = name;
        this.company = company;
        this.fee = fee;
        this.cardCondition = cardCondition;
        this.benefit = benefit;
        this.characterImage = characterImage;
    }


    // Getter와 Setter 메서드는 여기에 추가
}