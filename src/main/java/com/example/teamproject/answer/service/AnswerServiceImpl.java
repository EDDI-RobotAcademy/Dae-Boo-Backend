package com.example.teamproject.answer.service;

import com.example.teamproject.answer.entity.Answer;
import com.example.teamproject.answer.repository.AnswerRepository;
import com.example.teamproject.questionBoard.entity.Question;
import com.example.teamproject.questionBoard.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Answer detail(Long questionId) {
        Optional<Question> maybeQuestion = questionRepository.findByQuestionId(questionId);

        if(maybeQuestion.isEmpty()){
            log.info("존재하지 않는 문의입니다.");
            return null;
        }

        Question question = maybeQuestion.get();

        Optional<Answer> maybeAnswer = answerRepository.findByQuestionId(question);

        if(maybeAnswer.isEmpty()){
            log.info("답변이 존재하지 않습니다.");
            return null;
        }

        Answer answer = maybeAnswer.get();

        return answer;
    }

}
