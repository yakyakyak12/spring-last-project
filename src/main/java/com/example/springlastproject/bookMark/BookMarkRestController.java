package com.example.springlastproject.bookMark;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject.booklike.BookLikeRequest;
import com.example.springlastproject.booklike.BookLikeResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookMarkRestController {

    private final BookMarkService bookMarkService;

   
    // 책북마크 없으면 save 1 응답, 있으면 Delete -1 응답
    @PostMapping("/bookBark/check")
    public ResponseEntity<?> save(@RequestBody BookMarkRequest.checkDTO requestDTO) {
        System.out.println("북마크 실행됨?");
        BookMarkResponse.checkDTO responseDTO = bookMarkService.책북마크(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

}
