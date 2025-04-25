package org.sopt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.sopt.dto.PostResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostException.class)
    public ResponseEntity<PostResponse> handlePostException(PostException e) {
        return ResponseEntity.badRequest()
                .body(PostResponse.error(e.getErrorCode(), e.getMessage()));
    }
}
