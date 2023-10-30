package com.example.springlastproject.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.utils.ApiUtils;
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

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        System.out.println("logout 실행됨");
        session.invalidate();
        return ResponseEntity.ok().body(ApiUtils.success("로그아웃 성공"));
    }
}
