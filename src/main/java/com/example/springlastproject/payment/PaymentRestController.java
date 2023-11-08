package com.example.springlastproject.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject.bookreply.BookReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping("/payment/result")
    public ResponseEntity<?> home(@RequestBody PaymentRequest.puchaseDTO data) {
        System.out.println("페이먼트 진입 성공?");
        return ResponseEntity.ok().body(ApiUtils.success("성공"));
    }

}
