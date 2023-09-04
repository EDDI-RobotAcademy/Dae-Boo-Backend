package com.example.teamproject.card.controller;

import com.example.teamproject.card.controller.form.CardRequestForm;
import com.example.teamproject.card.entity.Card;
import com.example.teamproject.card.service.CardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    final private CardService cardService;
    @PostMapping("/manage/list")
    public List<Card> cardDetailList() {

        List<Card> cardList = cardService.getActivateCard();
        return cardList;
    }
    @PostMapping("/manage/register")
    public Card cardRegister (@RequestBody CardRequestForm form){
        Card card = cardService.cardRegister(form);
        return card;

    }
}

