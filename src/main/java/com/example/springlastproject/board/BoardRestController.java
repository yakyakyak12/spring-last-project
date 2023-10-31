package com.example.springlastproject.board;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.book.BookRequest;
import com.example.springlastproject.book.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/board/save")
    public ResponseEntity<?> save(@RequestBody @Valid BoardRequest.saveDTO saveDTO, Errors errors) {
        boardService.게시글등록(saveDTO);
        return null;
    }

    @DeleteMapping("/board/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        boardService.게시글삭제(id, userId);
        return null;
    }

}
