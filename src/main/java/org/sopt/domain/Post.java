package org.sopt.domain;
public class Post {
    private int id;
    private String title;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // 제목을 바꾸는 메서드 추가
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}

