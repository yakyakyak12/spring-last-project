package com.example.springlastproject.book;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.book.BookResponse.BookDetailPageDTO;
import com.example.springlastproject.book.BookResponse.BookCategoryListDTO;
import com.example.springlastproject.book.BookResponse.BookCategoryListDTO.BookListDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookRestController {

    private final BookService bookService;

    // 책 상세보기
    @PostMapping("/book/detail")
    public ResponseEntity<?> bookDetail(@RequestBody BookRequest.BookDetailDTO book) {
        BookDetailPageDTO bookResponseDTO = bookService.책상세보기(book);
        return ResponseEntity.ok(ApiUtils.success(bookResponseDTO));
    }

    // 책 목록보기
    @PostMapping("/book/bookList")
    public ResponseEntity<?> bookList(@RequestBody BookRequest.BookListDTO book) {
        System.out.println("bookList 호출");
        BookResponse.BookCategoryListDTO bookCategoryListDTO = bookService.한달이내출간된책(book);
        return ResponseEntity.ok(ApiUtils.success(bookCategoryListDTO));
    }
}
