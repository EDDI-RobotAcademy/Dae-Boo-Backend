package com.example.teamproject.QuestionBoard.service;

import com.example.teamproject.QuestionBoard.dto.QuestionDetailResponse;
import com.example.teamproject.QuestionBoard.dto.QuestionResponse;
import com.example.teamproject.QuestionBoard.repository.QuestionRepository;
import com.example.teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Override
    public List<QuestionResponse> retrieveAllByWriter(long userId) {
        return questionRepository.findAllByWriter(userId).stream()
                .map(QuestionResponse::from)
                .toList();
    }

    @Transactional
    public QuestionDetailResponse retrieve(long questionId) {
        return questionRepository.findById(questionId)
                .map(QuestionDetailResponse::from)
                .orElseThrow(() -> new RuntimeException("해당 1:1 문의에 대한 정보를 찾을 수 없습니다."));
    }
}
