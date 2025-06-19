package org.sopt.Exception;

import org.springframework.http.HttpStatus;

public enum PostErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."), // 🔧 추가된 부분
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, "제목은 비어 있을 수 없습니다."),
    EMPTY_CONTENT(HttpStatus.BAD_REQUEST, "내용은 비어 있을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    PostErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
