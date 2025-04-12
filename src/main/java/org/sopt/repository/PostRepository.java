package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    // 제목이 비어있으면 저장하지 않음
    public boolean save(Post post) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            return false; // 저장 실패
        }

        postList.add(post);
        return true; // 저장 성공
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }

        return null;
    }

    public boolean delete(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }
        return false;
    }
    // 게시글 제목을 수정하는 메서드 추가
    public boolean updateTitle(int id, String newTitle) {
        Post post = findPostById(id);
        if (post == null) return false;

        post.updateTitle(newTitle);
        return true;
    }
}