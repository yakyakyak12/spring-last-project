package com.example.springlastproject.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.user.UserResponse.LoginResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;
    private final UserJPARepository userJPARepository;
    private final HttpSession session;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO requestDTO, Errors errors) {
        System.out.println("join 실행됨");
        UserResponse.JoinDTO responseDTO = userService.회원가입(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 회원가입 중복체크
    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody UserRequest.CheckDTO requestDTO) {
        System.out.println("check 실행됨");
        System.out.println("유저네임에는 값을 받아 오는중인가? : " + requestDTO.getUsername());
        userService.중복체크(requestDTO.getUsername());
        return ResponseEntity.ok().body(ApiUtils.success("중복체크 성공"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO requestDTO, Errors errors) {
        System.out.println("login 실행됨");
        LoginResponseDTO responseDTO = userService.로그인(requestDTO);
        User user = userJPARepository.findByUsername(requestDTO.getUsername()).get();
        session.setAttribute("user", user);
        return ResponseEntity.ok().header("Authorization",
                responseDTO.getJwt()).body(ApiUtils.success(responseDTO.getUserDTO()));
    }

    // 개인정보수정 페이지
    @GetMapping("/user/updatePage")
    public ResponseEntity<?> updatePage(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        UserResponse.updatePageDTO responseDTO = userService.회원정보보기(userId);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 개인정보수정
    @PostMapping("/user/update")
    public ResponseEntity<?> updateForm(@RequestHeader("Authorization") String token,
            @RequestBody @Valid UserRequest.UpdateFormDTO requestDTO, Errors errors) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        System.out.println("개인정보 수정 진입 ");
        Integer userId = decodedJWT.getClaim("id").asInt();
        UserResponse.UpdateFormDTO responseDTO = userService.개인정보수정(requestDTO, userId);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));

    }

    // 회원탈퇴 페이지
    @GetMapping("/user/deletePage")
    public ResponseEntity<?> deletePage(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        UserResponse.BookStatusDTO responseDTO = userService.서재이용현황(userId);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 회원탈퇴 기능
    @GetMapping("/user/delete")
    public ResponseEntity<?> deleteForm(@RequestHeader("Authorization") String token) {
        System.out.println(token);

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
