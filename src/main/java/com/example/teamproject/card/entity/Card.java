package com.example.teamproject.card.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "Card")
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

    @Column(name = "character_image")
    private Boolean characterImage; // 열 이름 변경

    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate;

}
