package com.example.springlastproject.bookreply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
@RestController
public class BookReplyRestController {

    private final BookReplyService bookReplyService;
    private final BookReplyJPARepository bookReplyJPARepository;
    private final HttpSession session;

    // 댓글 등록
    @PostMapping("/bookReply/save")
    public ResponseEntity<?> save(@RequestBody @Valid BookReplyRequest.saveDTO saveDTO, Errors errors) {
        System.out.println("책댓글 save 실행됨");
        BookReplyResponse.saveDTO response = bookReplyService.댓글등록(saveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 댓글 삭제
    @DeleteMapping("/bookReply/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        System.out.println("세션 유저 id를 잘 받아오는가? : " + userId);
        System.out.println("댓글 삭제 댓글 ID:" + id);
        System.out.println("댓글 삭제 세션유저 ID:" + userId);
        bookReplyService.댓글삭제(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success("댓글 삭제 성공"));

    }

}
