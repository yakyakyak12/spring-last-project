package com.example.springlastproject.user;

import java.sql.Timestamp;

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

    private String username; // id

    private String password; // 비밀번호

    private String email; // 이메일

    private String nickname; // 닉네임

    private String picUrl; // 이미지

    @CreationTimestamp // now
    private Timestamp createdAt; // 등록일

    @Builder
    public User(Integer id, String username, String password, String email, String nickname, String picUrl,
            Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.picUrl = picUrl;
        this.createdAt = createdAt;
    }

}