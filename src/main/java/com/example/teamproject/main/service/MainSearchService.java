package com.example.teamproject.main.service;

import com.example.teamproject.card.entity.Card;
import com.example.teamproject.main.service.response.MainSearchResponse;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface MainSearchService {
    public MainSearchResponse search(String keyword);

}
