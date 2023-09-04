package com.example.teamproject.card.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "company")
    private String company;
    @Column(name = "fee")
    private String fee;
    @Column(name = "condition")
    private String condition;
    @Column(name = "benefit")
    private String benefit;
    @Column(name = "character")
    private Boolean character;
    @Column(name = "activate")
    private Boolean activate;
}
