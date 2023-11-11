package com.example.springlastproject.booklike;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookLikeRestController {

    private final BookLikeService bookLikeService;

    // 좋아요 한 책 페이지
    @GetMapping("/bookLike/page")
    public ResponseEntity<?> bookLikePage(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        BookLikeResponse.MyLikeBookDTO response = bookLikeService.좋아요한책조회(userId);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 책좋아요 없으면 save 1 응답, 있으면 Delete -1 응답
    @PostMapping("/bookLike/check")
    public ResponseEntity<?> save(@RequestBody BookLikeRequest.checkDTO requestDTO) {
        System.out.println("북마크 실행됨?");
        BookLikeResponse.checkDTO responseDTO = bookLikeService.책좋아요(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

}
