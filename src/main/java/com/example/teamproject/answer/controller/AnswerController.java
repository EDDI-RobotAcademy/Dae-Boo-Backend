package com.example.teamproject.answer.controller;

import com.example.teamproject.answer.entity.Answer;
import com.example.teamproject.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/details/{questionId}")
    public Answer getAnswerDetails(@PathVariable Long questionId) {
        log.info("getAnswerDetails()");

        return answerService.detail(questionId);
    }

}
