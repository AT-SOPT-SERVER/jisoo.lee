package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.request.CommentRequest;
import org.sopt.dto.response.CommentResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts/{postId}")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comments")
    public ResponseEntity<PostResponse<CommentResponse>> createComment(
            @PathVariable Long postId,
            @RequestHeader Long userId,
            @Valid @RequestBody CommentRequest request
    ) {
        CommentResponse commentResponse = commentService.create(postId, userId, request.getContent());
        return ResponseEntity.status(201).body(PostResponse.success(commentResponse));
    }

    // 게시글의 전체 댓글 조회
    @GetMapping("/comments")
    public ResponseEntity<PostResponse<List<CommentResponse>>> getCommentsByPost(
            @PathVariable Long postId
    ) {
        List<CommentResponse> responses = commentService.getByPostId(postId);
        return ResponseEntity.ok(PostResponse.success(responses));
    }

    // 댓글 단건 조회
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<PostResponse<CommentResponse>> getCommentById(
            @PathVariable Long commentId
    ) {
        CommentResponse commentResponse = commentService.getCommentById(commentId);
        return ResponseEntity.ok(PostResponse.success(commentResponse));
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<PostResponse<CommentResponse>> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestHeader Long userId,
            @Valid @RequestBody CommentRequest request
    ) {
        CommentResponse commentResponse = commentService.update(postId, commentId, userId, request.getContent());
        return ResponseEntity.ok(PostResponse.success(commentResponse));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<PostResponse<Void>> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestHeader Long userId
    ) {
        commentService.delete(postId, commentId, userId);
        return ResponseEntity.ok(PostResponse.success(null));
    }

    // 댓글 좋아요
    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<PostResponse<Void>> likeComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.likeComment(postId, commentId);
        return ResponseEntity.ok(PostResponse.success(null));
    }
}