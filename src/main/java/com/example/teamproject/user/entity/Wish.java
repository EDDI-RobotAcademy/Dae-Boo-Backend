package com.example.teamproject.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;
    private Long userId;
    private Long cardId;

    @Override
    public String toString() {
        return "Wish{" +
                "wishId=" + wishId +
                ", userId=" + userId +
                ", cardId=" + cardId +
                '}';
    }

    public Wish(Long userId, Long cardId) {
        this.userId = userId;
        this.cardId = cardId;
    }
}
