package com.example.springlastproject.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.utils.ApiUtils;
import com.example.springlastproject.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping("/payment/result")
    public ResponseEntity<?> home(@RequestBody PaymentRequest.PaymentDTO paymentDTO) {
        PaymentResponse.PaymentDTO response = paymentService.결재내역등록하기(paymentDTO);
        return ResponseEntity.ok().body(ApiUtils.success(response));
    }

}
