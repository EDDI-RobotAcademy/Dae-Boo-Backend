package com.example.teamproject.card.service;


import com.example.teamproject.card.controller.form.CardRequestForm;
import com.example.teamproject.card.controller.form.WishResponse;
import com.example.teamproject.card.entity.Card;
import com.example.teamproject.user.entity.User;

import java.util.List;


public interface CardService {

    List<Card> getActivateCard();

    Card cardRegister(CardRequestForm form);

    List<Card> getAgeCard();

    Boolean stopCard(Long id);

    Card retrieve(long cardId);

    List<Card> retrieveInterestList();

    // -------------------Wish Card-----------------------
    User getUserById(Long userId);

    Card getCardById(Long cardId);

    WishResponse wishCard(Long userId, Long cardId);

    List<Card> myWishCardList(Long userId);
}
