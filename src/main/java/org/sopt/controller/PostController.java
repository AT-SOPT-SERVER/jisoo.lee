package org.sopt.controller;

import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostResponseData;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody final PostRequest postRequest) {
        PostResponseData result = postService.createPost(postRequest.title()); // result 받기
        return ResponseEntity.ok(PostResponse.success(result)); // 실제 응답 전달
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(PostResponse.success(postService.getPostById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        PostResponseData result = postService.updatePostTitle(id, request.title());
        return ResponseEntity.ok(PostResponse.success(result));
    }


}
