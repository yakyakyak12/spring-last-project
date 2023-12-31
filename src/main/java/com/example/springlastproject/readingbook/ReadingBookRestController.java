package com.example.springlastproject.readingbook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ReadingBookRestController {

    private final ReadingBookService readingBookService;

    // 유저가 읽고 있는 책 목록
    @GetMapping("/readingbook/user")
    public ResponseEntity<?> MyReadingbook(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        ReadingBookResponse.MyBookDTO response = readingBookService.내가읽고있는책목록(userId);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 바로 읽기
    @GetMapping("/readingbook/{id}")
    public ResponseEntity<?> readingbook(@PathVariable Integer id,
            @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        System.out.println("id 값은 잘오나? : " + id);
        ReadingBookResponse.readingbookDTO response = readingBookService.바로읽기(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 읽고 있는 책 등록
    @PostMapping("/readingbook/save")
    public ResponseEntity<?> save(@RequestBody ReadingBookRequest.saveDTO requestDTO) {
        ReadingBookResponse.saveDTO responseDTO = readingBookService.읽고있는책등록(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 읽고 있는 책 삭제
    @DeleteMapping("/readingbook/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Integer id,
            @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        readingBookService.읽고있는책삭제(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success("읽고 있는 책 삭제 성공"));
    }
}
