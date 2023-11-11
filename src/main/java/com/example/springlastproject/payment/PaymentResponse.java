package com.example.springlastproject.payment;

import java.text.SimpleDateFormat;

import com.example.springlastproject.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PaymentResponse {

    @Getter
    @Setter
    @ToString
    public static class PaymentDTO {
        private Integer userId;
        private Boolean paymentStatus;
        private String name;
        private Integer amount;
        private String startDate;
        private String deadlineDate;

        public PaymentDTO(User user, Payment payment) {
            this.userId = user.getId();
            this.paymentStatus = user.getPaymentStatus();
            this.name = payment.getName();
            this.amount = payment.getAmount();
            this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(payment.getStartDate());
            this.deadlineDate = new SimpleDateFormat("yyyy-MM-dd").format(payment.getDeadlineDate());
        }

    }

    @Getter
    @Setter
    @ToString
    public static class MySettingMainPage {
        private Integer id;
        private String startDate;
        private String deadlineDate;
        private Integer months;

        public MySettingMainPage(Payment payment) {
            this.id = payment.getId();
            this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(payment.getStartDate());
            this.deadlineDate = new SimpleDateFormat("yyyy-MM-dd").format(payment.getDeadlineDate());
            this.months = payment.getMonths();
            
        }

    }
}
