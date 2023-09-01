package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.controller.form.BoardRequestForm;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.repository.BoardRepository;
import com.example.teamproject.logIn.entity.User;
import com.example.teamproject.logIn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public List<Board> list() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"));
    }


    //-------------------------MY PAGE--------------------------
//    @Override
//    public List<Board> myBoardList(Long userId) {
//        Optional<User> maybeUser = userRepository.findById(userId);
//        if (maybeUser.isPresent()) {
//            User user = maybeUser.get();
//            List<Board> BoardList = boardRepository.findAllByUserId(user);
//            log.info(BoardList.toString());
//            return BoardList;
//        }
//
//        return null;
//    }

    //User로 게시물을 다 찾음
    @Override
    public List<Board> findBoardByLoginUser(User LoginUser) {
        List<Board> maybeBoardList = boardRepository.findAllByUserId(LoginUser);
        log.info("maybeBoardList : " + maybeBoardList.toString());
        return maybeBoardList;
    }

    @Override
    public void register(BoardRequestForm boardRequestForm) {
        boardRepository.save(boardRequestForm.toBoard());
    }
}
