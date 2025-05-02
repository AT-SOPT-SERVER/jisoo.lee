package org.sopt.exception;

public class PostException extends RuntimeException {

    private final String errorCode;

    public PostException(String errorCode, String message) {
        super(message);  // RuntimeException의 메시지 설정
        this.errorCode = errorCode;
    }

    // errorCode 반환
    public String getErrorCode() {
        return errorCode;
    }

    // 부모 클래스에서 이미 제공된 getMessage()를 사용
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
