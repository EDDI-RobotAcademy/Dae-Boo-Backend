package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public List<Board> list() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"));
    }

    @Override
    public boolean delete(List<Long> boardIds) {
        boolean deletedAtLeastOneBoard = false;

        for (Long boardId : boardIds) {
            if (deleteBoard(boardId)) {
                deletedAtLeastOneBoard = true;
            }
        }

        return deletedAtLeastOneBoard;
    }

    private boolean deleteBoard(Long boardId) {
        Optional<Board> maybeBoard = boardRepository.findById(boardId);

        if(maybeBoard.isEmpty()) {
            log.info("존재하지 않는 게시판 입니다.");
            return false;
        }

        Board board = maybeBoard.get();

        // 예외 처리
        try {
            boardRepository.delete(board);
            return true;
        } catch (Exception e) {
            log.error("게시판 삭제 중 오류가 발생했습니다.", e);
            return false;
        }
    }

}
