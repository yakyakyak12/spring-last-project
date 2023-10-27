package com.example.springlastproject.booklike;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookLikeRestController {

    private final BookLikeService bookLikeService;

    @PostMapping("/bookLike/save")
    public ResponseEntity<?> save(@RequestBody BookLikeRequest.saveDTO saveDTO) {
        System.out.println("북마크 실행됨?");
        BookLikeResponse.saveDTO response = bookLikeService.북마크등록(saveDTO);
        return ResponseEntity.ok().body(response);
    }

}
