package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostResponseData;
import org.sopt.Exception.BaseException;
import org.sopt.Exception.PostErrorCode;
import org.sopt.Repository.PostRepository;
import org.sopt.Repository.UserRepository;
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

        return PostResponse.success(post.getTitle(), post.getContent(), post.getId());
    }

    public List<PostResponseData> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(post -> new PostResponseData(
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public PostResponseData getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(PostErrorCode.POST_NOT_FOUND));

        return new PostResponseData(
                post.getTitle(),
                post.getContent(),
                post.getUser().getName()
        );
    }

    public PostResponse updatePost(Long postId, PostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(PostErrorCode.POST_NOT_FOUND));

        post.update(request.getTitle(), request.getContent());
        return PostResponse.success(post.getTitle(), post.getContent(), post.getId());
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(PostErrorCode.POST_NOT_FOUND));

        postRepository.delete(post);
    }
}
