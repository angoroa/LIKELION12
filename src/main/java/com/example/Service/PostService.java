package com.example.Service;

import com.example.Repository.PostRepository;
import com.example.domain.Post;
import com.example.dto.PostDto;
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
    public Post createPost(Post post) { return postRepository.save(post);}
    public List<Post> getAllPost() { return postRepository.findAll();}
    public Optional<Post> getPostById(Long id) { return postRepository.findById(id);}
    public void deletePost(Long id) {postRepository.deleteById(id);}
    public Post updatePost(Long id, PostDto postDto) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (postDto.getTitle() != null) {
                post.setTitle(postDto.getTitle());
            }
            if (postDto.getContent() != null) {
                post.setContent(postDto.getContent());
            }
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with id" + id);
        }

    }
}
