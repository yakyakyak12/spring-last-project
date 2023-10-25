package com.example.springlastproject.book;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception404;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookJPARepository bookJPARepository;

    // (기능2) 책 상세보기
    public BookResponse.BookDetailPageDTO 책상세보기(Integer id) {
        Book book = bookJPARepository.findById(id).orElseThrow(() -> new Exception404("해당상품을 찾을수 없습니다."));
        return new BookResponse.BookDetailPageDTO(book);
    }
}
