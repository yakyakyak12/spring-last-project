package com.example.springlastproject.board;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;
import com.example.springlastproject.book.Book;
import com.example.springlastproject.book.BookJPARepository;
import com.example.springlastproject.book.BookRequest;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;

    public void 게시글등록(BoardRequest.saveDTO saveDTO) {
        boardJPARepository.save(saveDTO.toEntity());
    }

    public void 게시글삭제(Integer id, Integer userId) {
        Board board = boardJPARepository.findById(id).orElseThrow(() -> new Exception400("게시글 정보가 없습니다."));
        if (board.getUser().getId() == userId) {
            boardJPARepository.deleteById(id);
        } else {
            throw new Exception403("권한이 없습니다.");
        }
    }

}
