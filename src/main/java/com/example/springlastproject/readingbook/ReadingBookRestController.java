package com.example.springlastproject.readingbook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReadingBookRestController {

    private final ReadingBookService readingBookService;

    // 읽고 있는 책 등록
    @PostMapping("/readingbook/save")
    public ResponseEntity<?> save(@RequestBody ReadingBookRequest.saveDTO saveDTO) {
        ReadingBookResponse.saveDTO response = readingBookService.읽고있는책등록(saveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 읽고 있는 책 삭제
    @DeleteMapping("/readingbook/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        readingBookService.읽고있는책삭제(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success("읽고 있는 책 삭제 성공"));
    }
}
