package com.example.teamproject.card.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "card_detail")
public class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Fee")
    private String fee;

    @Column(name = "Results")
    private String results;

    @Column(name = "Benefit")
    private String benefit;

    }
