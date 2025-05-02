package org.sopt.dto;

public class PostResponse {

    private int code;
    private String message;
    private String title;
    private String content;  // ✅ 내용 추가
    private Long postId;

    public PostResponse(int code, String message, String title, String content, Long postId) {
        this.code = code;
        this.message = message;
        this.title = title;
        this.content = content;
        this.postId = postId;
    }

    public static PostResponse success(String title, String content, Long postId) {
        return new PostResponse(201, "요청이 정상적으로 처리되었습니다.", title, content, postId);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }
}
