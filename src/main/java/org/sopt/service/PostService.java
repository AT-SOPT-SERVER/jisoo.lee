package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private int postId = 1;

    //게시글 생성 시 제목이 비어있는지 검사
    public void createPost(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }

        //제목이 30자를 초과하는 경우
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이하여야 합니다.");
        }

        Post post = new Post(postId++, title);
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    //게시글 제목 수정을 위한 서비스 메서드 추가
    public boolean updatePostTitle(int id, String newTitle) {
        return postRepository.updateTitle(id, newTitle);
    }
}


// 제목 30자 제한 기능 최종 확인용 주석
