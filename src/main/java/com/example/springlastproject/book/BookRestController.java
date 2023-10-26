package com.example.springlastproject.book;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject.book.BookResponse.BookDetailPageDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/book/detail/{id}")
    public ResponseEntity<?> bookDetail(@PathVariable Integer id) {
        BookDetailPageDTO bookDTO = bookService.책상세보기(id);
        return ResponseEntity.ok(ApiUtils.success(bookDTO));
    }

    @GetMapping("/bookList/{id}")
    public ResponseEntity<?> bookList(@PathVariable Integer id) {

        return null;
    }
}
