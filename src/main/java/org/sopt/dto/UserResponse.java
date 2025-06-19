package org.sopt.dto;

public class UserResponse {

    private int code;
    private String message;
    private String name;
    private Long userId;

    public UserResponse(int code, String message, String name, Long userId) {
        this.code = code;
        this.message = message;
        this.name = name;
        this.userId = userId;
    }

    public static UserResponse success(String name, Long userId) {
        return new UserResponse(201, "회원가입이 완료되었습니다.", name, userId);
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public String getName() { return name; }
    public Long getUserId() { return userId; }
}
