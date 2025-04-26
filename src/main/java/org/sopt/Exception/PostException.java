package org.sopt.exception;

public class PostException extends RuntimeException {
    private final String errorCode;

    public PostException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
