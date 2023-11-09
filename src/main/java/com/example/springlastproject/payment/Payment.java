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

    @ManyToOne
    private User user;

    @Builder
    public Payment(Integer id, String name, Integer amount, Date startDate, Date deadlineDate, User user) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.user = user;
    }

}
