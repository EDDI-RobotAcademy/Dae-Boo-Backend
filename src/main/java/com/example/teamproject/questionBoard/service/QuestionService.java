package com.example.teamproject.questionBoard.service;

import com.example.teamproject.questionBoard.dto.QuestionDetailResponse;
import com.example.teamproject.questionBoard.dto.QuestionModifyRequest;
import com.example.teamproject.questionBoard.dto.QuestionResponse;
import com.example.teamproject.questionBoard.dto.QuestionWriteRequest;
import com.example.teamproject.questionBoard.entity.Question;

import java.util.List;

public interface QuestionService {
    List<QuestionResponse> retrieveAllByWriter(long userId);

    QuestionDetailResponse retrieve(long questionId);

    QuestionDetailResponse write(QuestionWriteRequest request);

    QuestionDetailResponse modify(long questionId, QuestionModifyRequest request);

    void delete(long questionId);

    List<Question> list();
}
