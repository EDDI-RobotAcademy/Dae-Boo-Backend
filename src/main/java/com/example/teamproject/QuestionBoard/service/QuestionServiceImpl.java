package com.example.teamproject.QuestionBoard.service;

import com.example.teamproject.QuestionBoard.dto.QuestionDetailResponse;
import com.example.teamproject.QuestionBoard.dto.QuestionModifyRequest;
import com.example.teamproject.QuestionBoard.dto.QuestionResponse;
import com.example.teamproject.QuestionBoard.dto.QuestionWriteRequest;
import com.example.teamproject.QuestionBoard.entity.Question;
import com.example.teamproject.QuestionBoard.repository.QuestionRepository;
import com.example.teamproject.user.entity.User;
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

    @Transactional
    public QuestionDetailResponse write(QuestionWriteRequest request) {
        User writer = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("올바르지 않은 작성자입니다."));

        Question question = new Question(writer, request.getTitle(), request.getContents());
        Question savedQuestion = questionRepository.save(question);
        return QuestionDetailResponse.from(savedQuestion);
    }

    @Transactional
    public QuestionDetailResponse modify(long questionId, QuestionModifyRequest request) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("해당 1:1 문의에 대한 정보를 찾을 수 없습니다."));

        // 이미 답변이 작성되었으면 수정할 수 없다.
        if (question.isAnswerComplete()) {
            throw new RuntimeException("이미 답변이 작성된 1:1 문의라 수정할 수 없습니다.");
        }
        question.modify(request.getTitle(), request.getContents());
        return QuestionDetailResponse.from(question);
    }
}
