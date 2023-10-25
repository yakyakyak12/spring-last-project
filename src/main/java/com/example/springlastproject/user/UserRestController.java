package com.example.springlastproject.user;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final HttpSession session;

    // 회원가입
    @PostMapping("/join")
    public String join() {
        return "회원가입";
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return null;
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }
}
