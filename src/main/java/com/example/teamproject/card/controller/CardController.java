package com.example.teamproject.card.controller;

import com.example.teamproject.card.controller.form.CardRequestForm;
import com.example.teamproject.card.entity.Card;
import com.example.teamproject.card.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    final private CardService cardService;

    @PostMapping("/manage/list")
    public List<Card> cardDetailList() {

        List<Card> cardList = cardService.getAllCard();
        return cardList;
    }

    @PostMapping("/manage/register")
    public Card cardRegister(@RequestBody CardRequestForm form) {
        Card card = cardService.cardRegister(form);
        return card;
    }

    @GetMapping("/age/list")
    public List<Card> cardAgeList() {

        List<Card> ageCardList = cardService.getAgeCard();
        // 아래의 내용은 추후 삭제 예정
        List<Card> cardList =new ArrayList<>();
        cardList.add(ageCardList.get(0));
        cardList.add(ageCardList.get(1));
        cardList.add(ageCardList.get(2));
        cardList.add(ageCardList.get(3));
        cardList.add(ageCardList.get(4));

        return cardList;
    }

    @PostMapping("/manage/stopCard")
    public Boolean cardStop(@RequestBody Long cardId) {
        return cardService.stopCard(cardId);
    }

    @GetMapping("/list")
    public List<Card> cardList() {
        List<Card> cardList = cardService.getActivateCard();
        return cardList;
    }

    @GetMapping("/interest/list")
    public List<Card> retrieveInterestCardList() {
        return cardService.retrieveInterestList();
    }

    @GetMapping("/interest-card")
    public Card retrieveCardDetail(@RequestParam long cardId) {
        return cardService.retrieve(cardId);
    }

    // 카드 찜하기 or 찜삭제하기
    @PostMapping("/wishCard")
    public void clickWishCard(@RequestParam Long userId,
                              @RequestParam Long cardId) {
        cardService.wishCard(userId, cardId);
    }

    // 내가 찜한카드 불러오기
    @GetMapping("/wishCardList")
    public List<Card> myWishCardList(@RequestParam Long userId) {
        log.info("myWishCardList()");
        return cardService.myWishCardList(userId);
    }

    // 카드 정보
    @GetMapping("/{cardId}")
    public Card cardInfo(@PathVariable("cardId") Long cardId) {
        log.info("CardInformation()");
        return cardService.getCardInfo(cardId);
    }
    @GetMapping("/manage/CardInfo")
    public Card getAccountInfo(@RequestParam Long cardId) {
        log.info("requestUserInfo()");
        return cardService.getUserInfo(cardId);
    }

    @GetMapping("/cardBenefit")
    public List<String> cardBenefit(@RequestParam Long cardId) {
        log.info("cardBenefit()");
        List<String> aaa = cardService.asdasdasd(cardId);
        return aaa;

    }
}

