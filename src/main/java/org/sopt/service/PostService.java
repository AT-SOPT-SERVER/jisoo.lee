package org.sopt.service;

import org.springframework.stereotype.Service;
import org.sopt.domain.Post;
import org.sopt.dto.PostResponseData;
import org.sopt.exception.PostException;
import org.sopt.Repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 🔹 게시글 생성
    public PostResponseData createPost(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new PostException("POST_001", "제목은 비어 있을 수 없습니다.");
        }

        if (title.length() > 30) {
            throw new PostException("POST_002", "제목은 30자 이하여야 합니다.");
        }

        Post post = new Post(title);
        Post savedPost = postRepository.save(post);

        return new PostResponseData(savedPost.getId(), savedPost.getTitle());
    }

    // 🔹 전체 게시글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 🔹 특정 게시글 상세 조회
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostException("POST_003", "해당 ID의 게시글이 없습니다."));
    }

    // 🔹 게시글 수정 (제목 변경)
    public PostResponseData updatePostTitle(Long id, String newTitle) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostException("POST_004", "해당 게시글이 존재하지 않습니다."));

        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new PostException("POST_005", "수정할 제목은 비어 있을 수 없습니다.");
        }

        post.updateTitle(newTitle); // 이 메서드는 Post.java에 반드시 있어야 함
        Post updated = postRepository.save(post);

        return new PostResponseData(updated.getId(), updated.getTitle());
    }
}
