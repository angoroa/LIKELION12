package com.example.Controller;

import com.example.Service.MemberService;
import com.example.Service.PostService;
import com.example.domain.Member;
import com.example.domain.Post;
import com.example.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final MemberService memberService; // 추가된 부분

    @Autowired
    public PostController(PostService postService, MemberService memberService) { // 수정된 부분
        this.postService = postService;
        this.memberService = memberService; // 수정된 부분
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        Post post = new Post();
        Optional<Member> memberOptional = memberService.getMemberById(postDto.getMemberId()); // 추가된 부분
        if (memberOptional.isPresent()) { // 추가된 부분
            post.setMember(memberOptional.get()); // 추가된 부분
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setPostDate(postDto.getPostDate());
            Post createdPost = postService.createPost(post);
            return ResponseEntity.ok(PostDto.from(createdPost));
        } else { // 추가된 부분
            return ResponseEntity.badRequest().build(); // 추가된 부분
        }
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPost();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto dto = PostDto.from(post);
            postDtos.add(dto);
        }
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        Optional<Post> postOptional = postService.getPostById(id);
        if (postOptional.isPresent()) {
            PostDto dto = PostDto.from(postOptional.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Long id, @RequestBody PostDto postDto) {
        try {
            Post updatedPost = postService.updatePost(id, postDto);
            return ResponseEntity.ok(PostDto.from(updatedPost));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}