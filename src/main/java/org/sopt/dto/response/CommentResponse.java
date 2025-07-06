package org.sopt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.domain.Comment;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private Long postId;
    private Long userId;
    private String content;
    private int likeCount;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getLikeCount()
        );
    }
}