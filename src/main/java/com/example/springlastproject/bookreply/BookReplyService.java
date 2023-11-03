package com.example.springlastproject.bookreply;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;
import com.example.springlastproject.book.Book;
import com.example.springlastproject.book.BookJPARepository;
import com.example.springlastproject.booklike.BookLike;
import com.example.springlastproject.booklike.BookLikeJPARepository;
import com.example.springlastproject.bookreply.BookReplyResponse.saveDTO;
import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookReplyService {
    private final BookReplyJPARepository bookReplyJPARepository;
    private final BookLikeJPARepository bookLikeJPARepository;
    private final UserJPARepository userJPARepository;

    public BookReplyResponse.bookReplyPage 한줄리뷰페이지(Integer userId) {
        List<BookLike> bookList = bookLikeJPARepository.findByUserId(userId);
        return new BookReplyResponse.bookReplyPage(bookList);
    }

    public saveDTO 댓글등록(BookReplyRequest.saveDTO saveDTO) {
        BookReply bookReply = bookReplyJPARepository.save(saveDTO.toEntity());
        User user = userJPARepository.findById(saveDTO.getUserId()).orElseThrow(() -> new Exception400("유저정보가 없습니다."));

        return new BookReplyResponse.saveDTO(bookReply, user);
    }

    public void 댓글삭제(Integer bookReplyId, Integer userId) {
        BookReply bookReply = bookReplyJPARepository.findById(bookReplyId)
                .orElseThrow(() -> new Exception400("댓글 정보가 없습니다."));
        if (bookReply.getUser().getId() == userId) {
            bookReplyJPARepository.deleteById(bookReplyId);
        } else {
            throw new Exception403("권한이 없습니다.");
        }
    }

}
