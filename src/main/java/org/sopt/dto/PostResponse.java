package org.sopt.dto;

public record PostResponse(
        int code,
        String message,
        String title,
        String content,
        Long postId
) {
    public static PostResponse success(String title, String content, Long postId) {
        return new PostResponse(201, "요청이 정상적으로 처리되었습니다.", title, content, postId);
    }
}
