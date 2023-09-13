package com.example.teamproject.home.controller;

import com.example.teamproject.home.service.response.MainSearchResponse;
import com.example.teamproject.home.service.MainSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainSearchController {

    private final MainSearchService mainSearchService;

    @GetMapping("/search")
    public MainSearchResponse searchByKeyword(@RequestParam String keyword) {
        return mainSearchService.search(keyword);
    }
}