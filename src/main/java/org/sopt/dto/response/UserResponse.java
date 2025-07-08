package org.sopt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private int code;
    private String message;
    private String name;
    private Long userId;

    public static UserResponse success(String name, Long userId) {
        return new UserResponse(201, "회원가입이 완료되었습니다.", name, userId);
    }
}