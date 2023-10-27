package com.example.springlastproject.book;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/book/detail/{id}")
    public ResponseEntity<?> bookDetail(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        System.out.println("북 디테일 접근하나?");
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        BookDetailPageDTO bookResponseDTO = bookService.책상세보기(id, userId);
        return ResponseEntity.ok(ApiUtils.success(bookResponseDTO));
    }

    @GetMapping("book/bookList")
    public ResponseEntity<?> bookList() {
        System.out.println("bookList 호출");
        BookResponse.BookCategoryListDTO bookCategoryListDTO = bookService.한달이내출간된책();
        return ResponseEntity.ok(ApiUtils.success(bookCategoryListDTO));
    }
}
