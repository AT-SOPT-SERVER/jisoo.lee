package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostResponseData;
import org.sopt.global.common.exception.BaseException;
import org.sopt.Repository.PostRepository;
import org.sopt.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Long createPost(Long userId, PostRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));

        Post post = new Post(request.getTitle(), request.getContent(), user);
        postRepository.save(post);
        return post.getId();
    }

    // ✅ 전체 조회 (최신순, 제목 + 작성자)
    public List<PostResponseData> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(post -> new PostResponseData(
                        post.getTitle(),
                        post.getContent(), // ✅ content도 넣어줌
                        post.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    // ✅ 상세 조회 (제목, 내용, 작성자)
    public PostResponseData getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        return new PostResponseData(post.getTitle(), post.getContent(), post.getUser().getName());
    }

    // ✅ 수정
    public PostResponse updatePost(Long postId, PostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        post.update(request.getTitle(), request.getContent());
        return PostResponse.success(post.getTitle(), post.getContent(), post.getId());
    }

    // ✅ 삭제
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        postRepository.delete(post);
    }
}
