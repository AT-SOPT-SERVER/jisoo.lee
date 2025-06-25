package org.sopt.controller;

import org.sopt.domain.Comment;
import org.sopt.dto.CommentRequest;
import org.sopt.dto.CommentResponse;
import org.sopt.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> create(
            @PathVariable Long postId,
            @RequestHeader Long userId,
            @RequestBody CommentRequest request
    ) {
        Comment comment = commentService.create(postId, userId, request.getContent());
        return ResponseEntity.ok(new CommentResponse(comment));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(
            @PathVariable Long postId
    ) {
        List<CommentResponse> responses = commentService.getByPostId(postId).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable Long commentId
    ) {
        Comment comment = commentService.getCommentById(commentId);
        return ResponseEntity.ok(new CommentResponse(comment));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestHeader Long userId,
            @RequestBody CommentRequest request
    ) {
        Comment comment = commentService.update(postId, commentId, userId, request.getContent());
        return ResponseEntity.ok(new CommentResponse(comment));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestHeader Long userId
    ) {
        commentService.delete(postId, commentId, userId);
        return ResponseEntity.ok().build();
    }

    // 댓글 좋아요
    @PostMapping("/posts/{postId}/comments/{commentId}/like")
    public ResponseEntity<Void> likeComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.likeComment(postId, commentId);
        return ResponseEntity.ok().build();
    }
}
