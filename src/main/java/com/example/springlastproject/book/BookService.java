package com.example.springlastproject.book;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception404;
// import com.example.springlastproject.book.BookResponse.BookDetailDTO;
import com.example.springlastproject.book.BookResponse.BookDetailDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookJPARepository bookJPARepository;

    public BookResponse.BookDetailDTO 상세보기(Integer id) {
        Book bookPs = bookJPARepository.findById(id).orElseThrow(() -> new Exception404("존재하지 않는 도서입니다. : " + id));
        return new BookDetailDTO(bookPs);
    }

    // 책 리스트
    public List<BookResponse.FindAllDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<Book> pageContent = bookJPARepository.findAll(pageable);
        List<BookResponse.FindAllDTO> responseDTO = pageContent.getContent().stream()
                .map(book -> new BookResponse.FindAllDTO(book))
                .collect(Collectors.toList());
        return responseDTO;
    }
}
