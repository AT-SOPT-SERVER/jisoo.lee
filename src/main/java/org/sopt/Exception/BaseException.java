package org.sopt.Exception;

import org.sopt.Exception.PostErrorCode;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private final PostErrorCode errorCode;

    public BaseException(PostErrorCode errorCode) {
        super(errorCode.getMessage());  // RuntimeException의 message 필드에 에러 메시지를 저장
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();  // enum에서 설정한 HttpStatus 반환
    }

    public String getErrorMessage() {
        return errorCode.getMessage();  // enum에서 설정한 메시지 반환
    }

    public String getErrorCode() {
        return errorCode.name(); // "USER_NOT_FOUND" 또는 "POST_NOT_FOUND" 같은 코드 이름 반환
    }
}
