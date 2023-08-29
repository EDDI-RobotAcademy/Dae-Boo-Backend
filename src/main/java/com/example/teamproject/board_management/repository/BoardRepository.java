package com.example.teamproject.board_management.repository;

import com.example.teamproject.board_management.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
