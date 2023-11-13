package com.example.springlastproject.board;

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
import com.example.springlastproject.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;
    private final HttpSession session;

    // 피드 페이지
    @GetMapping("/boardList")
    public ResponseEntity<?> feedPage() {
        BoardResponse.BoardListDTO responseDTO = boardService.게시글전체조회하기();
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 게시글 상세보기 페이지
    @GetMapping("/boardDetail/{id}")
    public ResponseEntity<?> boardDetail(@PathVariable Integer id) {

        BoardResponse.BoardDetailPageDTO responseDTO = boardService.게시글상세보기(id);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 게시글 등록하기
    @PostMapping("/board/save")
    public ResponseEntity<?> save(@RequestBody @Valid BoardRequest.saveDTO requestDTO, Errors errors) {
        System.out.println("DTO안에 보드 값은? : " + requestDTO.getBookId());
        BoardResponse.saveDTO responseDTO = boardService.게시글등록(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 게시글 수정 페이지 목록
    @GetMapping("/board/{id}/updatePage")
    public ResponseEntity<?> updatePage(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        BoardResponse.updatePageDTO responseDTO = boardService.게시글수정화면(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 게시글 수정 요청
    @PostMapping("/board/{id}/update")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.updateDTO requestDTO) {
        BoardResponse.updateDTO responseDTO = boardService.게시글수정하기(id, requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));

    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}/delete")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable Integer id,
            @RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        boardService.게시글삭제(id, userId);
        return ResponseEntity.ok().body(ApiUtils.success("게시글 삭제 성공"));
    }

}
