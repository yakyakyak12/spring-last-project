package com.example.springlastproject.payment;

import lombok.Getter;
import lombok.Setter;

public class PaymentRequest {
    @Getter
    @Setter
    public static class puchaseDTO {
        private Integer userId;
        private String pay;

        public puchaseDTO() {
        }

        public puchaseDTO(Integer userId, String pay) {
            this.userId = userId;
            this.pay = pay;
        }

    }

}