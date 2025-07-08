package org.sopt.service;

import org.sopt.Exception.BaseException;
import org.sopt.Exception.ErrorCode;
import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.response.CommentResponse;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    //댓글 작성
    public CommentResponse create(Long postId, Long userId, String content) {
        Post post = findPostById(postId);
        User user = findUserById(userId);
        Comment saved = commentRepository.save(new Comment(content, post, user));
        return CommentResponse.from(saved);
    }

    //게시글의 전체 댓글 조회
    public List<CommentResponse> getByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    //댓글 단건 조회
    public CommentResponse getCommentById(Long commentId){
        Comment comment = findCommentById(commentId);
        return CommentResponse.from(comment);
    }

    //댓글 수정
    public CommentResponse update(Long postId, Long commentId, Long userId, String content){
        Comment comment = findCommentById(commentId);

        if (!comment.getUser().getId().equals(userId)) {
            throw new BaseException(ErrorCode.UNAUTHORIZED_COMMENT_MODIFICATION);
        }

        comment.update(content);
        return CommentResponse.from(commentRepository.save(comment));
    }

    //댓글 삭제
    public void delete(Long postId, Long commentId, Long userId){
        Comment comment = findCommentById(commentId);

        if (!comment.getUser().getId().equals(userId)) {
            throw new BaseException(ErrorCode.UNAUTHORIZED_COMMENT_DELETION);
        }
        commentRepository.delete(comment);
    }

    //댓글 좋아요
    public void likeComment(Long postId, Long commentId){
        Comment comment = findCommentById(commentId);

        if (!comment.getPost().getId().equals(postId)) {
            throw new BaseException(ErrorCode.INVALID_COMMENT_POST_RELATION);
        }

        comment.increaseLike();
        commentRepository.save(comment);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.POST_NOT_FOUND));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
