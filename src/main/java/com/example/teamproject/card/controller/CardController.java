package com.example.teamproject.card.controller;

import com.example.teamproject.card.entity.CardDetail;
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

    @PostMapping("/list")
    public List<CardDetail> cardDetailList (){
    return cardService.getcard();
    }
}

