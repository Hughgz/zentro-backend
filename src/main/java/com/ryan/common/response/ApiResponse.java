package com.ryan.common.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private Instant timestamp;
    private T data;

    public static <T> ApiResponse<T> success(int statusCode, String message, T data){
        return ApiResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .timestamp(Instant.now())
                .data(data)
                .build();
    }
    public static <T> ApiResponse<T> error(int statusCode, String message) {
        return ApiResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .timestamp(Instant.now())
                .data(null)
                .build();
    }
}
