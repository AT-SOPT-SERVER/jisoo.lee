package org.sopt.Exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_404","유저를 찾을 수 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_404","게시글을 찾을 수 없습니다."),
    EMPTY_TITLE(HttpStatus.BAD_REQUEST,  "POST_001","제목은 비어 있을 수 없습니다."),
    EMPTY_CONTENT(HttpStatus.BAD_REQUEST, "POST_002","내용은 비어 있을 수 없습니다."),
    EMPTY_NAME(HttpStatus.BAD_REQUEST, "USER_001", "이름은 비어 있을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT_404", "댓글을 찾을 수 없습니다."),
    UNAUTHORIZED_COMMENT_MODIFICATION(HttpStatus.UNAUTHORIZED, "COMMENT_401_MODIFY", "댓글 수정 권한이 없습니다."),
    UNAUTHORIZED_COMMENT_DELETION(HttpStatus.UNAUTHORIZED, "COMMENT_401_DELETE", "댓글 삭제 권한이 없습니다."),
    INVALID_COMMENT_POST_RELATION(HttpStatus.BAD_REQUEST, "COMMENT_400_RELATION", "댓글이 해당 게시글에 속하지 않습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}