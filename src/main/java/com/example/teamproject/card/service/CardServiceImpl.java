package com.example.teamproject.card.service;

import com.example.teamproject.card.controller.form.CardRequestForm;
import com.example.teamproject.card.controller.form.WishResponse;
import com.example.teamproject.card.entity.Card;
import com.example.teamproject.card.entity.Wish;
import com.example.teamproject.card.repository.CardRepository;
import com.example.teamproject.card.repository.WishRepository;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.repository.UserRepository;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;

//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    final private CardRepository cardRepository;
    final private UserRepository userRepository;
    final private WishRepository wishRepository;

    // 현재 오류나고 있습니다. 추후 수정 바랍니다.
    // @Value("${spring.web.cors.allowed-origins}")
    // private String[] allowedOrigins;

    @Override
    public List<Card> getActivateCard() {
        List<Card> cardList = cardRepository.findByActivateTrue();
        return cardList;
    }

    @Override
    public Card cardRegister(CardRequestForm form) {
        Card newCard = form.toCard();
        Optional<Card> maybeCard = cardRepository.findByName(newCard.getName());
        if (maybeCard.isEmpty()) {
            return cardRepository.save(newCard);
        }
        return null;
    }

    @Override
    public List<Card> getAgeCard() {
//        RestTemplate restTemplate = new RestTemplate();
//        String fastApiUrl = "http://15.165.11.253:3002/age-recommend-card";
//        String response = restTemplate.getForObject(fastApiUrl, String.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Card> ageCardList = new ArrayList<>();
//        try {
//            List<String> ageCardNumberList = objectMapper.readValue(response, new TypeReference<List<String>>() {
//            });
//            log.info(ageCardNumberList.toString());
//            for (String cardNumber : ageCardNumberList) {
//                Optional<Card> maybeCardNumber = cardRepository.findByCardId(Long.valueOf(cardNumber));
//                if (maybeCardNumber.isPresent()) {
//                    ageCardList.add(maybeCardNumber.get());
//                } else {
//                    log.info("없는 카드 번호 입니다.");
//                    return null;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
        return cardRepository.findAll();
    }

    @Override
    public Boolean stopCard(Long id) {
        Optional<Card> maybeCard = cardRepository.findById(id);
        if (maybeCard.isPresent()) {
            Card targetCard = maybeCard.get();
            targetCard.setActivate(false);
            cardRepository.save(targetCard);

            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Card retrieve(long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("잘못된 카드정보입니다."));
        card.increaseViewCount();
        return card;
    }

    @Override
    public List<Card> retrieveInterestList() {
        return cardRepository.findTop10ByActivateTrueOrderByViewCountDesc();
    }

    // -------------------Wish Card-----------------------
    @Override
    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("등록된 User가 아닙니다. : " + userId));
    }

    @Override
    public Card getCardById(Long cardId) {
        return cardRepository.findByCardId(cardId)
                .orElseThrow(() -> new RuntimeException("등록된 Card가 아닙니다. : " + cardId));
    }

    @Override
    public WishResponse wishCard(Long userId, Long cardId) {
        User user = getUserById(userId);
        Card card = getCardById(cardId);
        Optional<Wish> maybeWish = wishRepository.findByUserAndCard(userId, cardId);

        if (maybeWish.isPresent()) {
            log.info("this wish is present");
            Boolean isWish = false; //찜 안 된 상태면 찜삭제

            Wish wish = maybeWish.get();
            wishRepository.delete(wish);
            return new WishResponse(isWish, userId, cardId);
        } else {
            log.info("this wish is empty");
            Boolean isWish = true; //찜 된 상태

            Wish wish = new Wish();
            wish.setUser(user);
            wish.setCard(card);

            wishRepository.save(wish); //찜 된 상태면 찜추가
            return new WishResponse(isWish, userId, cardId);
        }

    }

    @Override
    public List<Card> myWishCardList(Long userId){
        log.info("myWishCardList()");
        List<Wish> wishes = wishRepository.findAllByUserId(userId);
        List<Card> wishCardList = new ArrayList<>();
        for (Wish element: wishes) {
            System.out.println(element.getCard().getCardId());
            wishCardList.add(element.getCard());
        }
        return wishCardList;
    }


    @Override
    public Card getCardInfo(Long cardId) {
        Optional<Card> maybeCard = cardRepository.findByCardId(cardId);
        if(maybeCard.isEmpty()) {
            log.info("정보가 없습니다!");
        }
        return maybeCard.get();
    }
    @Override
    public Card getUserInfo(Long cardId) {
        Optional<Card> maybeCard = cardRepository.findById(cardId);
        if (maybeCard.isEmpty()){
            return null;
        }
        return maybeCard.get();
    }

    @Override
    public List<String> asdasdasd(long cardId){
        log.info("asdasdasd()=>" + cardId);
        Optional<Card> maybeCard = cardRepository.findByCardId(cardId);
        String cardBenefit = "";
        List<String> categoryCode = new ArrayList<>();
        if(maybeCard.isPresent()){
            cardBenefit =  maybeCard.get().getBenefit();
            log.info("cardBenefit : " + cardBenefit);
        }
        if(cardBenefit.contains("교육")){
            categoryCode.add("AC5");
        }
        if(cardBenefit.contains("대형마트")){
            categoryCode.add("MT1");
        }
        if(cardBenefit.contains("문화")){
            categoryCode.add("CT1");
        }
        if(cardBenefit.contains("외식")){
            categoryCode.add("FD6");
        }
        if(cardBenefit.contains("주유")){
            categoryCode.add("OL7");
        }
        if(cardBenefit.contains("카페")){
            categoryCode.add("CE7");
        }
        if(cardBenefit.contains("편의점")){
            categoryCode.add("CS2");
        }
        log.info("categoryCode : " + categoryCode);

        return categoryCode;
    }

}