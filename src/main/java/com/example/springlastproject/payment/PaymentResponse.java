package com.example.springlastproject.payment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        private List<PaymentDetailsDTO> PaymentDetailsList;

        public MySettingMainPage(List<Payment> payments) {
            this.PaymentDetailsList = payments.stream().map(payment -> new PaymentDetailsDTO(payment))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public static class PaymentDetailsDTO {
            private Integer id;
            private String name;
            private Integer amount;
            private String startDate;
            private String deadlineDate;
            private String subNumber;
            private Boolean refund;
            private String refundDate;

            public PaymentDetailsDTO(Payment payment) {
                this.id = payment.getId();
                this.name = payment.getName();
                this.amount = payment.getAmount();
                this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(payment.getStartDate());
                this.deadlineDate = new SimpleDateFormat("yyyy-MM-dd").format(payment.getDeadlineDate());
                this.subNumber = payment.getSubNumber();
                this.refund = payment.getRefund();
                this.refundDate = (payment.getRefundDate() != null)
                        ? new SimpleDateFormat("yyyy-MM-dd").format(payment.getRefundDate())
                        : null;
            }
        }

    }
}
