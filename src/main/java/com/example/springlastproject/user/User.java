package com.example.springlastproject.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "user_tb")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20)
    private String username; // id

    @Column(length = 100)
    private String password; // 비밀번호

    @Column(length = 100)
    private String email; // 이메일

    @Column(length = 20, nullable = true)
    private String nickname; // 닉네임

    @Column(nullable = true)
    private String picUrl; // 이미지

    @CreationTimestamp // now
    private Timestamp createdAt; // 등록일

    private Boolean paymentStatus = false; // 결재 상태

    @Builder
    public User(Integer id, String username, String password, String email, String nickname, String picUrl,
            Timestamp createdAt, Boolean paymentStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.picUrl = picUrl;
        this.createdAt = createdAt;
        this.paymentStatus = paymentStatus;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}