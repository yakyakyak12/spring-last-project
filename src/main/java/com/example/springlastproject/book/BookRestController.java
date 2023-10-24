package com.example.springlastproject.book;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.utils.ApiUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/books/{id}")
    public ResponseEntity addBooks(@PathVariable Integer id) {
        BookResponse.BookDetailDTO responseDTOs = bookService.상세보기(id);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTOs));
    }

    // 책 리스트
    @GetMapping("/books")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        List<BookResponse.FindAllDTO> responseDTO = bookService.findAll(page);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
}
