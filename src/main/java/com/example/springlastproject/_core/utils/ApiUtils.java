package com.example.springlastproject._core.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(1, "성공", response);
    }

    public static <T> ApiResult<T> error(String message, T response) {
        return new ApiResult<>(-1, message, response);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApiResult<T> {
        private final int code;
        private final String msg;
        private final T data;
    }

    // @Getter
    // @Setter
    // @AllArgsConstructor
    // public static class ApiError {
    // private final String message;
    // private final int status;
    // }
}
