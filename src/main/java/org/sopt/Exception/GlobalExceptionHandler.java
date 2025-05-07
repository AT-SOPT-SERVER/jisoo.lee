package org.sopt.Exception;

import org.sopt.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ 1. DTO 유효성 검사 실패 처리 (예: @NotBlank 실패)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();

        ErrorResponse response = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                errorMsg
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // ✅ 2. 커스텀 예외 처리 (BaseException)
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBase(BaseException e) {
        ErrorResponse response = ErrorResponse.of(
                e.getStatus().value(),
                e.getMessage()
        );

        return ResponseEntity.status(e.getStatus()).body(response);
    }
}
