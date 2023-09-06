package com.example.teamproject.QuestionBoard.controller;

import com.example.teamproject.QuestionBoard.dto.QuestionDetailResponse;
import com.example.teamproject.QuestionBoard.dto.QuestionResponse;
import com.example.teamproject.QuestionBoard.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class UserQuestionController {

    private final QuestionService questionService;

    // 내가 쓴 1:1 문의 목록 조회 API
    @GetMapping("/user/{userId}")
    public List<QuestionResponse> retrieveMyQuestionList(@PathVariable long userId) {
        return questionService.retrieveAllByWriter(userId);
    }

    // 1:1 문의 상세조회 API
    @GetMapping("/inquiry/{id}")
    public QuestionDetailResponse retrieveQuestionDetail(@PathVariable(name = "id") long questionId) {
        return questionService.retrieve(questionId);
    }
}
