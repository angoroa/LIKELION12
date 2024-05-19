package com.example.Service;

import com.example.Repository.PostRepository;
import com.example.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) { this.postRepository = postRepository;}
    @Transactional
    public Post registerPost(Post post) { return postRepository.save(post);}
    public List<Post> getAllPost() { return postRepository.findAll();}
    public Optional<Post> getPostById(Long id) { return postRepository.findById(id);}
}
