package org.sopt.dto;

import org.sopt.domain.Comment;

public class CommentResponse {
    private Long postId;
    private Long userId;
    private Long commentId;
    private String content;
    private int likeCount;

    public CommentResponse(Comment comment) {
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
    }

    public Long getPostId() {
        return postId;
    }
    public Long getUserId() {
        return userId;
    }
    public Long getCommentId() {
        return commentId;
    }
    public String getContent() {
        return content;
    }
    public int getLikeCount() {
        return likeCount;
    }
}
