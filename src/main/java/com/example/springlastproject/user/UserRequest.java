package com.example.springlastproject.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserRequest {
    @Getter
    @Setter
    @ToString
    public static class JoinDTO {

        @NotEmpty()
        private String username;

        @NotEmpty()
        private String email;

        @NotEmpty()
        @Size(min = 8, max = 20, message = "8에서 20자 이내여야 합니다.")
        private String password;

        @NotEmpty()
        private String nickname;

        private String picUrl = "basic.jpg";

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .picUrl(picUrl)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class LoginDTO {
        @NotEmpty
        private String uesrname;
        @NotEmpty
        private String password;

    }

    @Getter
    @Setter
    @ToString
    public static class CheckDTO {
        @NotEmpty
        private String username;
    }
}