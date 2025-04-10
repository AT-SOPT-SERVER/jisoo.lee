package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    // 제목이 비었거나 30자를 초과하면 에러 메시지를 출력하고 저장하지 않음
    public void createPost(final String title) {
        postService.createPost(title);
    }


    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    // 게시글 제목 수정을 위한 컨트롤러 메서드 추가
    public boolean updatePostTitle(int id, String newTitle) {
        return postService.updatePostTitle(id, newTitle);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return null;
    }

}