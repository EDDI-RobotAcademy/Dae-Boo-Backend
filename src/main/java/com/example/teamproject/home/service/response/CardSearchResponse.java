package com.example.teamproject.home.service.response;

import com.example.teamproject.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardSearchResponse {

    private long cardId;
    private String name;
    private String company;
    private String fee;
    public static CardSearchResponse from(Card card) {
        return new CardSearchResponse(card.getCardId(), card.getName(), card.getCompany(), card.getFee());
    }
}
