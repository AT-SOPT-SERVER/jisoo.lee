package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostResponse;
import org.sopt.Exception.BaseException;
import org.sopt.Exception.ErrorCode;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    //게시글 작성하기
    public PostResponse<PostDetailResponse> createPost(Long userId, PostRequest request) {
        User user = findUserById(userId);

        Post post = new Post(request.getTitle(), request.getContent(), user);
        postRepository.save(post);

        return PostResponse.success(PostDetailResponse.from(post));
    }

    //게시글 전체 조회
    public PostResponse<Page<PostDetailResponse>> getAllPosts(int page) {
        PageRequest pageable = PageRequest.of(page, 10);
        Page<PostDetailResponse> posts = postRepository.findAllByOrderByIdDesc(pageable)
                .map(PostDetailResponse::from);

        return PostResponse.success(posts);
    }

    //게시글 상세 조회
    public PostResponse<PostDetailResponse> getPostById(Long postId) {
        Post post = findPostById(postId);

        return PostResponse.success(PostDetailResponse.from(post));
    }

    //게시글 수정
    @Transactional
    public PostResponse<PostDetailResponse> updatePost(Long postId, PostRequest request) {
        Post post = findPostById(postId);

        post.update(request.getTitle(), request.getContent());
        return PostResponse.success(PostDetailResponse.from(post));
    }

    //게시글 삭제
    public PostResponse<Void> deletePost(Long postId) {
        Post post = findPostById(postId);

        postRepository.delete(post);
        return PostResponse.success(null);
    }

    //게시글 좋아요
    @Transactional
    public PostResponse<Void> increaseLike(Long postId) {
        Post post = findPostById(postId);

        post.increaseLike();
        return PostResponse.success(null);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.POST_NOT_FOUND));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    }
}
