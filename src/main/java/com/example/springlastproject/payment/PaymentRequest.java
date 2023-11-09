package com.example.springlastproject.payment;

import lombok.Getter;
import lombok.Setter;

public class PaymentRequest {
    @Getter
    @Setter
    public static class PaymentDTO {
        private Integer userId;
        private String pay;

        public PaymentDTO() {
        }

        public PaymentDTO(Integer userId, String pay) {
            this.userId = userId;
            this.pay = pay;
        }

    }

}