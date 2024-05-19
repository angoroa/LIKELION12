package com.example.Service;

import com.example.Repository.CommentRepository;
import com.example.domain.Comment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository;}
    @Transactional
    public Comment registerComment(Comment comment) { return commentRepository.save(comment);}
    public List<Comment> getAllComment() { return commentRepository.findAll();}
    public Optional<Comment> getCommentById(Long id) { return commentRepository.findById(id);}



}
