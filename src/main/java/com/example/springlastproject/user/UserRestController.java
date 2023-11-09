package com.example.springlastproject.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.user.UserResponse.LoginResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;
    private final HttpSession session;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO requestDTO, Errors errors) {
        System.out.println("join 실행됨");
        UserResponse.JoinDTO response = userService.회원가입(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 회원가입 중복체크
    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody UserRequest.CheckDTO rCheckDTO) {
        System.out.println("check 실행됨");
        System.out.println("유저네임에는 값을 받아 오는중인가? : " + rCheckDTO.getUsername());
        userService.중복체크(rCheckDTO.getUsername());

        return ResponseEntity.ok().body(ApiUtils.success("중복체크 성공"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO requestDTO, Errors errors) {
        System.out.println("login 실행됨");
        LoginResponseDTO response = userService.로그인(requestDTO);
        return ResponseEntity.ok().header("Authorization",
                response.getJwt()).body(ApiUtils.success(response.getUserDTO()));
    }

    // 개인정보수정 페이지
    @GetMapping("/user/updatePage")
    public ResponseEntity<?> updatePage(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        UserResponse.updatePageDTO response = userService.회원정보보기(userId);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 개인정보수정
    @PostMapping("/user/{id}/update")
    public ResponseEntity<?> updateForm(@PathVariable Integer id, @RequestHeader("Authorization") String token,
            @RequestBody @Valid UserRequest.UpdateFormDTO requestDTO, Errors errors) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        if (userId != id) {
            throw new Exception400("권한이 없습니다");
        }
        UserResponse.UpdateFormDTO response = userService.개인정보수정(requestDTO, id);
        return ResponseEntity.ok().body(ApiUtils.success(response));

    }

    // 회원탈퇴 페이지
    @GetMapping("/user/deletePage")
    public ResponseEntity<?> deletePage(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        UserResponse.BookStatusDTO response = userService.서재이용현황(userId);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 회원탈퇴 기능
    @GetMapping("/user/delete")
    public ResponseEntity<?> deleteForm(@RequestHeader("Authorization") String token) {
        System.out.println("컨트롤러");
        System.out.println(token);
        System.out.println("탈퇴");

        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        userService.회원탈퇴(userId);
        return ResponseEntity.ok().body(ApiUtils.success("회원탈퇴 완료"));
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        System.out.println("logout 실행됨");
        session.invalidate();
        return ResponseEntity.ok().body(ApiUtils.success("로그아웃 완료"));
    }
}
