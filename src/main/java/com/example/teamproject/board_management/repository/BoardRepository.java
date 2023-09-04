package com.example.teamproject.board_management.repository;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // Board b 의 b는 별칭입니다.
    @Query("SELECT b FROM Board b WHERE b.boardStatus.state != 0")
    List<Board> findActiveBoards();

    List<Board> findAllByUserId(User userId);
}
