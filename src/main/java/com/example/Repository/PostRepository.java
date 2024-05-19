package com.example.Repository;

import com.example.domain.Member;
import com.example.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
