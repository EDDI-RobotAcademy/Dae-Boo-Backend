package com.example.teamproject.QuestionBoard.repository;

import com.example.teamproject.QuestionBoard.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.user.userId = :userId")
    List<Question> findAllByWriter(@Param("userId") long userId);

    List<Question> findByIsDeletedFalse();
}
