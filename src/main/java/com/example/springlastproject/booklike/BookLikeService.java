package com.example.springlastproject.booklike;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookLikeService {

    private final BookLikeJPARepository bookLikeJPARepository;

    public BookLikeResponse.saveDTO 북마크등록(BookLikeRequest.saveDTO saveDTO) {
        BookLike response = bookLikeJPARepository.save(saveDTO.toEntity());
        return new BookLikeResponse.saveDTO(response);
    }

}
