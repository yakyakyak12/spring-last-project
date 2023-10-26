package com.example.springlastproject.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserResponse {

    @Getter
    @Setter
    public static class LoginResponseDTO {
        private int id;
        private String jwt;
        private String username;
        private String email;
        private String nickname;
        private String picUrl;
        private String createdAt;

        public LoginResponseDTO(User user, String jwt) {
            this.id = user.getId();
            this.jwt = jwt;
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.picUrl = user.getPicUrl();
            this.createdAt = new SimpleDateFormat("yyyy-MM-dd").format(user.getCreatedAt());
        }
    }

    @Getter
    @Setter
    @ToString
    public static class JoinDTO {
        private Integer id;
        private String username;
        private String email;
        private String nickname;
        private String picUrl;
        private String createdAt;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.picUrl = user.getPicUrl();
            this.createdAt = new SimpleDateFormat("yyyy-MM-dd").format(user.getCreatedAt());
        }

    }

}
