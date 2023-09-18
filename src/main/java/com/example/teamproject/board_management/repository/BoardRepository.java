package com.example.teamproject.board_management.repository;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByUserId(User userId);

//    @Query("select b from Board b join fetch b.userId bu join fetch b.activate ba where bu.userId = :userId and ba.activate = true")
//    List<Board> findAllByUserIdAndActivateTrue(@Param("userId")User userId);
    List<Board> findAllByUserIdAndActivateTrue(User userId);
    Optional<Board> findByBoardId(Long boardId);

    @Query("SELECT b FROM Board b WHERE b.boardName LIKE %:keyword% OR b.content LIKE %:keyword%")
    List<Board> findAllByKeyword(@Param("keyword") String keyword);

    List<Board> findByActivateTrue(Sort boardId);
}
