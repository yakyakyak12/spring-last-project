package com.example.springlastproject.booklike;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject.board.Board;
import com.example.springlastproject.board.BoardJPARepository;
import com.example.springlastproject.boardreply.BoardReply;
import com.example.springlastproject.boardreply.BoardReplyJPARepository;
import com.example.springlastproject.bookreply.BookReply;
import com.example.springlastproject.bookreply.BookReplyJPARepository;
import com.example.springlastproject.readingbook.ReadingBook;
import com.example.springlastproject.readingbook.ReadingBookJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookLikeService {

    private final BookLikeJPARepository bookLikeJPARepository;
    private final ReadingBookJPARepository readingBookJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final BoardReplyJPARepository boardReplyJPARepository;
    private final BookReplyJPARepository bookReplyJPARepository;

    public BookLikeResponse.checkDTO 책좋아요(BookLikeRequest.checkDTO requestDTO) {
        BookLike bookLike = bookLikeJPARepository.findFirstByBookIdAndUserId(requestDTO.getBookId(), requestDTO.getUserId());
        if (bookLike == null) {
            bookLikeJPARepository.save(requestDTO.toEntity());
            return new BookLikeResponse.checkDTO(1);
        } else {
            bookLikeJPARepository.deleteById(bookLike.getId());
            return new BookLikeResponse.checkDTO(-1);
        }

    }

    // 내 서재 페이지 필요한 데이터
    public BookLikeResponse.BookOfMineDTO 내서재(Integer userId) {
        List<BookLike> bookLikeList = bookLikeJPARepository.findByUserId(userId);
        List<ReadingBook> readingBookList = readingBookJPARepository.findByUserId(userId);
        List<Board> boardList = boardJPARepository.findByUserId(userId);
        List<BoardReply> boardReplyList = boardReplyJPARepository.findByUserId(userId);
        List<BookReply> bookReply = bookReplyJPARepository.findByUserId(userId);

        return new BookLikeResponse.BookOfMineDTO(bookLikeList, readingBookList, boardList, boardReplyList, bookReply);
    }

    public BookLikeResponse.MyLikeBookDTO 좋아요한책조회(Integer userId) {
        List<BookLike> bookLikes = bookLikeJPARepository.findAllByUserId(userId);
        return new BookLikeResponse.MyLikeBookDTO(bookLikes);
    }
}
