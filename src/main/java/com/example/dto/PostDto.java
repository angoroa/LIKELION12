package com.example.dto;


import com.example.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime postDate;
    private MemberDto member;

    public static PostDto from(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setPostDate(post.getPostDate());
        //Post 엔티티 객체에서 PostDto 객체로 데이터를 변환하는 과정에서 Member 객체가 존재할 경우 이를 MemberDto로 변환하여 PostDto에 설정하는 역할
        if (post.getMember() != null){
            postDto.setMember(MemberDto.from(post.getMember()));
        }
        return postDto;
    }
}