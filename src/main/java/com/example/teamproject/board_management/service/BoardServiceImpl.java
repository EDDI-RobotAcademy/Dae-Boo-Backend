package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public List<Board> list() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"));
    }
}
