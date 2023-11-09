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
    private final UserService userService;

    @PostMapping("/payment/result")
    public ResponseEntity<?> home(@RequestBody PaymentRequest.PaymentDTO paymentDTO) {
        System.out.println("페이먼트 진입1111");
        userService.결재상태변경(paymentDTO.getUserId());
        System.out.println("페이먼트 진입2222");
        return ResponseEntity.ok().body(ApiUtils.success("성공"));
    }

}
