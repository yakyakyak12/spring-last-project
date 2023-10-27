package com.example.springlastproject.bookreply;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookReplyRestController {

    private final BookReplyService bookReplyService;
    private final BookReplyJPARepository bookReplyJPARepository;

    // 댓글 등록
    @PostMapping("/bookReply/save")
    public ResponseEntity<?> save(@RequestBody @Valid BookReplyRequest.saveDTO saveDTO, Errors errors) {
        System.out.println("책댓글 save 실행됨");
        BookReplyResponse.saveDTO bookReply = bookReplyService.댓글등록(saveDTO);
        return ResponseEntity.ok().body(bookReply);
    }

    // 댓글 삭제
    // @DeleteMapping("/bookReply/{id}/delete")
    // public @ResponseBody ResponseEntity<?> delete(@PathVariable Integer id) {

    // }

}
