package com.example.springlastproject.payment;

import lombok.Getter;
import lombok.Setter;

public class PaymentRequest {
    @Getter
    @Setter
    public static class PaymentDTO {
        private Integer userId;
        private String name;
        private Integer amount;
        private Integer months;
        private String subNumbar;

        public PaymentDTO() {
        }

    }

}