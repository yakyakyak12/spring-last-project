package com.example.springlastproject.booklike;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.utils.ApiUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookLikeRestController {

    private final BookLikeService bookLikeService;

    // 책좋아요 없으면 save 1 응답, 있으면 Delete -1 응답
    @PostMapping("/bookLike/check")
    public ResponseEntity<?> save(@RequestBody BookLikeRequest.checkDTO saveDTO) {
        System.out.println("북마크 실행됨?");
        BookLikeResponse.checkDTO response = bookLikeService.책좋아요(saveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

}
