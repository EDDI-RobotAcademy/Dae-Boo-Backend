package com.example.teamproject.QuestionBoard.service;

import com.example.teamproject.QuestionBoard.dto.QuestionDetailResponse;
import com.example.teamproject.QuestionBoard.dto.QuestionResponse;
import com.example.teamproject.QuestionBoard.dto.QuestionWriteRequest;

import java.util.List;

public interface QuestionService {
    List<QuestionResponse> retrieveAllByWriter(long userId);

    QuestionDetailResponse retrieve(long questionId);

    QuestionDetailResponse write(QuestionWriteRequest request);
}
