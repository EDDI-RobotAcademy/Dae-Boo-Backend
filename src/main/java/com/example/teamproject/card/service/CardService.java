package com.example.teamproject.card.service;


import com.example.teamproject.card.controller.form.CardRequestForm;
import com.example.teamproject.card.entity.Card;

import java.util.List;


public interface CardService {


    List<Card> getActivateCard();

    Card cardRegister(CardRequestForm form);
}
