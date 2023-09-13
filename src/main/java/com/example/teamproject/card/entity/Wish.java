package com.example.teamproject.card.entity;

import com.example.teamproject.card.entity.Card;
import com.example.teamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @ManyToOne
    @JoinColumn(name="cardId")
    private Card card;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
