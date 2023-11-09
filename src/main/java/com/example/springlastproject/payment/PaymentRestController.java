package com.example.springlastproject.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject._core.utils.JwtTokenUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PaymentRestController {

    private final PaymentService paymentService;

    // 내정보메인페이지
    @GetMapping("/payment/page")
    public ResponseEntity<?> paymentPage(@RequestHeader("Authorization") String token) {
        DecodedJWT decodedJWT = JwtTokenUtils.verify(token);
        Integer userId = decodedJWT.getClaim("id").asInt();
        PaymentResponse.MySettingMainPage response = paymentService.내정보메인페이지(userId);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

    // 결재하기
    @PostMapping("/payment/result")
    public ResponseEntity<?> home(@RequestBody PaymentRequest.PaymentDTO paymentDTO) {
        PaymentResponse.PaymentDTO response = paymentService.결재내역등록하기(paymentDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

}
