package com.example.teamproject.answer.repository;

import com.example.teamproject.answer.entity.Answer;
import com.example.teamproject.questionBoard.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQuestionId(Question question);
}
