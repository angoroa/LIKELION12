package com.example.Service;

import com.example.Repository.CommentRepository;
import com.example.Repository.MemberRepository;
import com.example.Repository.PostRepository;
import com.example.domain.Comment;
import com.example.domain.Member;
import com.example.domain.Post;
import com.example.dto.CommentDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository) {this.commentRepository = commentRepository;
    this.postRepository = postRepository;
    this.memberRepository = memberRepository;
    }
    @Transactional
    public Comment registerComment(Comment comment, Long postId, Long memberId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (postOptional.isPresent() && memberOptional.isPresent()) {
            comment.setPost(postOptional.get());
            comment.setMember(memberOptional.get());
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Not found");
        }
    }
    public List<Comment> getAllComments() { return commentRepository.findAll();}
    public Optional<Comment> getCommentById(Long id) { return commentRepository.findById(id);}
    public void deleteComment(Long id) {commentRepository.deleteById(id);}
    public Comment updateComment(Long id, CommentDto commentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            // 업데이트할 필드들을 설정합니다. CommentDto 클래스의 getter 메서드를 사용합니다.
            if (commentDto.getContent() != null) {
                comment.setContent(commentDto.getContent());
            }

            // 만약 CommentDto에 postId와 memberId가 포함되어 있다면
            if (commentDto.getPostId() != null) {
                Optional<Post> postOptional = postRepository.findById(commentDto.getPostId());
                postOptional.ifPresent(comment::setPost);
            }

            if (commentDto.getMemberId() != null) {
                Optional<Member> memberOptional = memberRepository.findById(commentDto.getMemberId());
                memberOptional.ifPresent(comment::setMember);
            }

            // 변경된 Comment 객체를 저장합니다.
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Comment not found");
        }
    }


}
