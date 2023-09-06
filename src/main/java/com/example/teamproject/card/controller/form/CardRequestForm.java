package com.example.teamproject.card.controller.form;

import com.example.teamproject.card.entity.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CardRequestForm {
    final private String name;

    final private String company;

    final private String fee;

    final private String cardCondition; // 열 이름 변경

    final private String benefit;

    final private Boolean characterImage; // 열 이름 변경

    public Card toCard() {
        return new Card(name,company,fee,cardCondition,benefit,characterImage);
    }
}
