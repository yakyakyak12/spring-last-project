package com.example.springlastproject.booklike;

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
public class BookLikeRestController {

    private final BookLikeService bookLikeService;

    @PostMapping("/bookLike/save")
    public ResponseEntity<?> save(@RequestBody BookLikeRequest.saveDTO saveDTO) {
        System.out.println("북마크 실행됨?");
        BookLikeResponse.saveDTO response = bookLikeService.북마크등록(saveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    @DeleteMapping("/bookLike/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        bookLikeService.북마크삭제(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success("북마크 삭제 성공"));

    }

}
