package com.example.springlastproject.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserRequest {
    @Getter
    @Setter
    @ToString
    public static class JoinDTO {

        @NotEmpty(message = "공백을 허용하지 않습니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "알파벳과 숫자만 허용됩니다.")
        @Size(min = 3, max = 12, message = "3에서 12자 사이여야 합니다.")
        private String username;

        @NotEmpty(message = "공백을 허용하지 않습니다.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "올바른 이메일 주소 형식이어야 합니다.")
        private String email;

        @NotEmpty(message = "공백을 허용하지 않습니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "알파벳과 숫자만 허용됩니다.")
        @Size(min = 4, max = 12, message = "4에서 12자 사이여야 합니다.")
        private String password;

        @NotEmpty(message = "공백을 허용하지 않습니다.")
        @Size(min = 2, max = 10, message = "2에서 10자 사이여야 합니다.")
        private String nickname;

        private String picUrl = "basic.jpg";

        public JoinDTO() {
        }

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .picUrl(picUrl)
                    .paymentStatus(false)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class LoginDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;

        public LoginDTO() {
        }
    }

    @Getter
    @Setter
    @ToString
    public static class CheckDTO {
        @NotEmpty
        private String username;

        public CheckDTO() {
        }

    }

    @Getter
    @Setter
    @ToString
    public static class UpdateFormDTO {
        @NotEmpty(message = "공백을 허용하지 않습니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "알파벳과 숫자만 허용됩니다.")
        @Size(min = 4, max = 12, message = "4에서 12자 사이여야 합니다.")
        private String password;
        @NotEmpty(message = "공백을 허용하지 않습니다.")
        @Size(min = 2, max = 10, message = "2에서 10자 사이여야 합니다.")
        private String nickname;
        @NotEmpty(message = "공백을 허용하지 않습니다.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "올바른 이메일 주소 형식이어야 합니다.")
        private String email;

        public UpdateFormDTO() {
        }

    }
}