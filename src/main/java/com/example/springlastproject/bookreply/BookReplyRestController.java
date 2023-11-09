package com.example.springlastproject.bookreply;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class BookReplyRestController {

    private final BookReplyService bookReplyService;
    private final HttpSession session;

    // 책 댓글 페이지
    @GetMapping("/book/{id}/bookReply")
    public ResponseEntity<?> bookReplys(@PathVariable Integer id) {
        BookReplyResponse.BookReplyDTO response = bookReplyService.책댓글보기(id);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 한줄 리뷰 페이지
    @GetMapping("/bookReply/page")
    public ResponseEntity<?> bookReplyPage(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        BookReplyResponse.bookReplyPage response = bookReplyService.한줄리뷰페이지(userId);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 책 댓글 등록
    @PostMapping("/bookReply/save")
    public ResponseEntity<?> save(@RequestBody @Valid BookReplyRequest.saveDTO saveDTO, Errors errors) {
        System.out.println("책댓글 save 실행됨");
        BookReplyResponse.saveDTO response = bookReplyService.댓글등록(saveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 책 댓글 삭제
    @DeleteMapping("/bookReply/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        bookReplyService.댓글삭제(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success("댓글 삭제 성공"));
    }
}
