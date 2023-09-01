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
    @CrossOrigin(origins = "http://ec2-15-165-11-253.ap-northeast-2.compute.amazonaws.com:3000")
    @PostMapping("/list")
    public List<CardDetail> cardDetailList() {
        // Your logic to retrieve and return the card details
        List<CardDetail> cardDetails = cardService.getcard();
        return cardDetails;
    }
}

