package org.sopt.dto;

public record PostResponse(
        int code,
        String message,
        Long postId,
        String title,
        String content,
        Long userId

) {
    public static PostResponse success(String title, String content, Long postId, Long userId) {
        return new PostResponse(201, "요청이 정상적으로 처리되었습니다.",  postId, title, content, userId);
    }
}
