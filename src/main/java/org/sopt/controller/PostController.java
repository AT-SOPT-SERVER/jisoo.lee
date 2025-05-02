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

    // ✅ 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestHeader Long userId,
            @Valid @RequestBody PostRequest postRequest
    ) {
        Long postId = postService.createPost(userId, postRequest);
        PostResponse response = PostResponse.success(
                postRequest.getTitle(),
                postRequest.getContent(),
                postId
        );
        return ResponseEntity.status(201).body(response);
    }

    // ✅ 게시글 전체 조회 (최신순, 제목 + 작성자만)
    @GetMapping
    public ResponseEntity<List<PostResponseData>> getAllPosts() {
        List<PostResponseData> responses = postService.getAllPosts();
        return ResponseEntity.ok(responses);
    }

    // ✅ 게시글 상세 조회 (제목, 내용, 작성자)
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseData> getPostById(@PathVariable Long postId) {
        PostResponseData response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    // ✅ 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequest request
    ) {
        PostResponse response = postService.updatePost(postId, request);
        return ResponseEntity.ok(response);
    }

    // ✅ 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
