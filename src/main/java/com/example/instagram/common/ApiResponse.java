package com.example.instagram.common;

import com.example.instagram.enums.ResponseCode;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private int statusCode;

    private String message;

    private String responseCode;

    private T data;

    public ApiResponse(ResponseCode result, T data) {
        this.statusCode = result.getStatusCode();
        this.message = result.getMessage();
        this.responseCode = result.getResponseCode();
        this.data = data;
    }

    public static <T> ApiResponse<T> of(ResponseCode result, T data) {
        return new ApiResponse<>(result, data);
    }
    public static ApiResponse<?> of(ResponseCode result) {
        return new ApiResponse<>(result, "");
    }
}
