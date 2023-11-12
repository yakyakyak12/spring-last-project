package com.example.springlastproject.payment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.springlastproject.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "payment_tb")
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // 상품명

    private Integer amount; // 금액

    private Date startDate; // 결재 시작일

    private Date deadlineDate; // 결재 마감일

    private Integer months; // 결재 개월수

    private Integer subNumber; // 이용권 번호

    private Boolean refund; // 디폴트 false, 환불 시 true

    private Date refundDate; // 환불 날짜(null 허용, 환불 시 날짜 생성)

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Payment(Integer id, String name, Integer amount, Date startDate, Date deadlineDate, Integer months,
            Integer subNumber, Boolean refund, Date refundDate, User user) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.months = months;
        this.subNumber = subNumber;
        this.refund = refund;
        this.refundDate = refundDate;
        this.user = user;
    }

    

}
