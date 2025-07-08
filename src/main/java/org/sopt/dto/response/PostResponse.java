package org.sopt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class PostResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    public static <T> PostResponse<T> success(T data) {
        return new PostResponse<>(201, "요청이 성공적으로 처리되었습니다.", data);
    }
}