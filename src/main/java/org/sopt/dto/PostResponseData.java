package org.sopt.dto;

public class PostResponseData {

    private String title;
    private String writer;
    private String content; // ✅ 상세 조회용으로 추가

    // 전체 조회용 생성자 (title + writer만)
    public PostResponseData(String title, String writer) {
        this.title = title;
        this.writer = writer;
    }

    // 상세 조회용 생성자 (title + content + writer)
    public PostResponseData(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }
}
