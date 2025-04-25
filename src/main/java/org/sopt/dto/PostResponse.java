package org.sopt.dto;

public record PostResponse(
        String errorCode,
        String message,
        Object result
) {
    public static PostResponse success(Object result) {
        return new PostResponse(null, "SUCCESS", result);
    }

    public static PostResponse error(String code, String message) {
        return new PostResponse(code, message, null);
    }
}
