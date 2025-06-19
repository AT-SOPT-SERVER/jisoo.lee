package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostResponseData;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // ✅ 게시글 생성 (응답 DTO는 서비스에서 생성)
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestHeader Long userId,
            @Valid @RequestBody PostRequest postRequest
    ) {
        PostResponse response = postService.createPost(userId, postRequest);
        return ResponseEntity.status(201).body(response);
    }

    // ✅ 전체 조회
    @GetMapping
    public ResponseEntity<List<PostResponseData>> getAllPosts() {
        List<PostResponseData> responses = postService.getAllPosts();
        return ResponseEntity.ok(responses);
    }

    // ✅ 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseData> getPostById(@PathVariable Long postId) {
        PostResponseData response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    // ✅ 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequest request
    ) {
        PostResponse response = postService.updatePost(postId, request);
        return ResponseEntity.ok(response);
    }

    // ✅ 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
