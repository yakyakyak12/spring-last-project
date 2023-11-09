package com.example.springlastproject.payment;

import lombok.Getter;
import lombok.Setter;

public class PaymentRequest {
    @Getter
    @Setter
    public static class PaymentDTO {
        private Integer userId;
        private String pay;
        private Integer amount;

        public PaymentDTO() {
        }

    }

}