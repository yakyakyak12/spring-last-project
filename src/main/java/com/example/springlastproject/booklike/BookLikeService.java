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

    public BookLikeResponse.saveDTO 북마크등록(BookLikeRequest.saveDTO saveDTO) {
        BookLike bookLike = bookLikeJPARepository.findFirstByBookIdAndUserId(saveDTO.getBookId(), saveDTO.getUserId());
        if (bookLike == null) {
            BookLike response = bookLikeJPARepository.save(saveDTO.toEntity());
            return new BookLikeResponse.saveDTO(response);
        }else {
            throw new Exception400("북마크를 두번 할 수 없습니다.");
        }

    }

    public void 북마크삭제(Integer bookId, Integer userId) {
     BookLike bookLike = bookLikeJPARepository.findFirstByBookIdAndUserId(bookId, userId);
     System.out.println("booLike id를 찾아오나? : " + bookLike.getId());
     bookLikeJPARepository.deleteById(bookLike.getId());
    }

}
