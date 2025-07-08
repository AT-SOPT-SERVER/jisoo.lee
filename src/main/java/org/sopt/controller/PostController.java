package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.service.PostService;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.response.PostResponse;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponse<PostDetailResponse>> createPost(
            @RequestHeader Long userId,
            @Valid @RequestBody PostRequest postRequest
    ) {
        PostResponse<PostDetailResponse> response = postService.createPost(userId, postRequest);
        return ResponseEntity.status(201).body(response);
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<PostResponse<Page<PostDetailResponse>>> getAllPosts(
            @RequestParam(defaultValue = "0") int page
    ) {
        PostResponse<Page<PostDetailResponse>> response = postService.getAllPosts(page);
        return ResponseEntity.ok(response);
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse<PostDetailResponse>> getPostById(@PathVariable Long postId) {
        PostResponse<PostDetailResponse> response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse<PostDetailResponse>> updatePost(
            @PathVariable Long postId,
            @Valid @RequestBody PostRequest request
    ) {
        PostResponse<PostDetailResponse> response = postService.updatePost(postId, request);
        return ResponseEntity.ok(response);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostResponse<Void>> deletePost(@PathVariable Long postId) {
        PostResponse<Void> response = postService.deletePost(postId);
        return ResponseEntity.ok(response);
    }

    // 게시글 좋아요
    @PostMapping("/{postId}/like")
    public ResponseEntity<PostResponse<Void>> likePost(@PathVariable Long postId) {
        PostResponse<Void> response = postService.increaseLike(postId);
        return ResponseEntity.ok(response);
    }
}