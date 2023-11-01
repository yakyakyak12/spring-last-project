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
        private String jwt;
        private UserDTO userDTO;

        public LoginResponseDTO(User user, String jwt) {
            this.jwt = jwt;
            this.userDTO = new UserDTO(user);
        }

        @Getter
        @Setter
        @ToString
        public static class UserDTO {
            private int id;
            private String username;
            private String email;
            private String nickname;
            private String picUrl;
            private String createdAt;

            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
                this.email = user.getEmail();
                this.nickname = user.getNickname();
                this.picUrl = user.getPicUrl();
                this.createdAt = new SimpleDateFormat("yyyy-MM-dd").format(user.getCreatedAt());
            }

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

    @Getter
    @Setter
    @ToString
    public static class updatePageDTO {
        private Integer id;
        private String picUrl = "basic.jpg";
        private String nickname;
        private String email;

        public updatePageDTO(User user) {
            this.id = user.getId();
            this.picUrl = user.getPicUrl();
            this.nickname = user.getNickname();
            this.email = user.getEmail();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class UpdateFormDTO {
        private Integer id;
        private String nickname;
        private String password;
        private String email;

        public UpdateFormDTO(User user) {
            this.id = user.getId();
            this.nickname = user.getNickname();
            this.password = user.getPassword();
            this.email = user.getEmail();
        }

    }

}
