package com.example.teamproject.board_management.repository;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByUserId(User userId);

    List<Board> findByActivateTrue();

    Optional<Board> findByBoardId(Long boardId);

    @Query("SELECT b FROM Board b WHERE b.boardName LIKE %:keyword% OR b.content LIKE %:keyword%")
    List<Board> findAllByKeyword(@Param("keyword") String keyword);
}
