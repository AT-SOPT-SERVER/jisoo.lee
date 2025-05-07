package org.sopt.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int code;
    private final String message;

    private ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(int code, String message) {
        return new ErrorResponse(code, message);
    }
}
