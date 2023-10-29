package com.example.springlastproject.booklike;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;
import com.example.springlastproject.bookreply.BookReply;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookLikeService {

    private final BookLikeJPARepository bookLikeJPARepository;

    public BookLikeResponse.checkDTO 책좋아요(BookLikeRequest.checkDTO saveDTO) {
        BookLike bookLike = bookLikeJPARepository.findFirstByBookIdAndUserId(saveDTO.getBookId(), saveDTO.getUserId());
        if (bookLike == null) {
            bookLikeJPARepository.save(saveDTO.toEntity());
            return new BookLikeResponse.checkDTO(1);
        }else {
            bookLikeJPARepository.deleteById(bookLike.getId());
            return new BookLikeResponse.checkDTO(-1);
        }

    }

}
