package com.example.teamproject.card.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class WishResponse {
    private Boolean isWish;
    private Long userId;
    private Long cardId;
}
