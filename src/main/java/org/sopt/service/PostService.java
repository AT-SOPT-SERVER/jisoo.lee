package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostResponseData;
import org.sopt.Exception.BaseException;
import org.sopt.Exception.PostErrorCode;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PostResponse createPost(Long userId, PostRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(PostErrorCode.USER_NOT_FOUND));

        Post post = new Post(request.getTitle(), request.getContent(), user);
        postRepository.save(post);

        return PostResponse.success(
                post.getTitle(),
                post.getContent(),
                post.getId(),
                post.getUser().getId());
    }

    public Page<PostResponseData> getAllPosts(int page) {
        PageRequest pageable = PageRequest.of(page, 10);
        return postRepository.findAllByOrderByIdDesc(pageable)
                .map(post -> new PostResponseData(
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getName(),
                        post.getComments().stream()
                                .map(comment -> comment.getContent())
                                .collect(Collectors.toList()),
                        post.getLikeCount()
                ));
    }

    public PostResponseData getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(PostErrorCode.POST_NOT_FOUND));

        return new PostResponseData(
                post.getTitle(),
                post.getContent(),
                post.getUser().getName(),
                post.getComments().stream()
                        .map(comment -> comment.getContent())
                        .collect(Collectors.toList()),
                post.getLikeCount()
        );
    }

    public PostResponse updatePost(Long postId, PostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(PostErrorCode.POST_NOT_FOUND));

        post.update(request.getTitle(), request.getContent());
        return PostResponse.success(
                post.getTitle(),
                post.getContent(),
                post.getId(),
                post.getUser().getId()
        );
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(PostErrorCode.POST_NOT_FOUND));

        postRepository.delete(post);
    }

    public void increaseLike(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.increaseLike();
        postRepository.save(post);
    }

}

