package org.sopt.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.Comment;
import org.sopt.domain.Post;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class PostDetailResponse {
    private final Long postId;
    private final String title;
    private final String content;
    private final String writer;
    private final List<String> comments;
    private final int likeCount;

    public static PostDetailResponse from(Post post) {
        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getName(),
                post.getComments().stream()
                        .map(Comment::getContent)
                        .collect(Collectors.toList()),
                post.getLikeCount()
        );
    }
}