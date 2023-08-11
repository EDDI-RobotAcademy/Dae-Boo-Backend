package com.example.teamproject.board.repository;


import com.example.teamproject.board.entity.JpaBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<JpaBoard, Long> {
}
