package com.example.springlastproject.book;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.book.BookResponse.BookDetailPageDTO;
import com.example.springlastproject.booklike.BookLikeResponse;
import com.example.springlastproject.booklike.BookLikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookRestController {

    private final BookService bookService;
    private final BookLikeService bookLikeService;

    // 책 상세보기
    @GetMapping("/book/detail/{id}")
    public ResponseEntity<?> bookDetail(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        BookDetailPageDTO bookResponseDTO = bookService.책상세보기(id, userId);
        return ResponseEntity.ok(ApiUtils.success(bookResponseDTO));
    }

    // 서점 베스트 (인기 목록보기)
    @PostMapping("/book/rankingList")
    public ResponseEntity<?> rankingList(@RequestBody BookRequest.rankingListDTO requestDTO) {
        BookResponse.BookCategoryListDTO responseDTO = bookService.지금서점베스트(requestDTO);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

    // 한달이내 출간된 책 목록보기
    @PostMapping("/book/monthList")
    public ResponseEntity<?> bookList(@RequestBody BookRequest.MonthListDTO book) {
        System.out.println("bookList 호출");
        BookResponse.BookCategoryListDTO responseDTO = bookService.한달이내출간된책(book);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

    // 책 검색화면
    @GetMapping("/book/searchPage")
    public ResponseEntity<?> searchPage() {
        BookResponse.BookSearchPageDTO responseDTO = bookService.검색화면();
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

    // 카테고리 별 책목록
    @PostMapping("/book/bookCategory")
    public ResponseEntity<?> bookCategory(@RequestBody BookRequest.BookCategoryDTO requestDTO) {
        BookResponse.BookCategoryDTO responseDTO = bookService.카테고리별목록보기(requestDTO);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

    // 검색 기능
    @PostMapping("/book/search")
    public ResponseEntity<?> searcg(@RequestBody BookRequest.BookSearchDTO requestDTO) {
        BookResponse.BookSearchDTO responseDTO = bookService.키워드검색(requestDTO);
        return ResponseEntity.ok(ApiUtils.success(responseDTO));
    }

    // 내 서재
    @GetMapping("/book/bookOfMine")
    public ResponseEntity<?> bookOfMine(@RequestHeader("Authorization") String token) {
        // 토큰으로 id 받음
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();

        BookLikeResponse.BookOfMineDTO response = bookLikeService.내서재(userId);

        return ResponseEntity.ok(ApiUtils.success(response));
    }

}
