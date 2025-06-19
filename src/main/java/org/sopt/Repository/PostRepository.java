package org.sopt.Repository;

import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // ✅ 게시글 전체 조회 (최신순)
    List<Post> findAllByOrderByIdDesc();
}
